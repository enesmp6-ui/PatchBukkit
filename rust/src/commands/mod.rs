use pumpkin::{
    command::{
        CommandExecutor, CommandSender,
        args::{Arg, ArgumentConsumer, GetClientSideArgParser, SuggestResult},
    },
    entity::EntityBase,
};
use pumpkin_protocol::java::client::play::{
    ArgumentType, StringProtoArgBehavior, SuggestionProviders,
};
use tokio::sync::{mpsc, oneshot};

use crate::java::jvm::commands::{JvmCommand, Location, Rotation};

pub struct JavaCommandExecutor {
    pub cmd_name: String,
    pub command_tx: mpsc::Sender<JvmCommand>,
}

#[derive(Clone)]
pub enum SimpleCommandSender {
    Console,
    /// UUID
    Player(String),
}

pub struct AnyCommandNode {
    command_name: String,
    command_tx: mpsc::Sender<JvmCommand>,
}

impl GetClientSideArgParser for AnyCommandNode {
    fn get_client_side_parser(&self) -> ArgumentType<'_> {
        ArgumentType::String(StringProtoArgBehavior::GreedyPhrase)
    }

    fn get_client_side_suggestion_type_override(&self) -> Option<SuggestionProviders> {
        Some(SuggestionProviders::AskServer)
    }
}

impl ArgumentConsumer for AnyCommandNode {
    fn consume<'a>(
        &'a self,
        _sender: &'a pumpkin::command::CommandSender,
        _server: &'a pumpkin::server::Server,
        args: &mut pumpkin::command::tree::RawArgs<'a>,
    ) -> pumpkin::command::args::ConsumeResult<'a> {
        let first_word_opt = args.pop();

        let mut msg = match first_word_opt {
            Some(word) => word.to_string(),
            None => return Box::pin(async { None }),
        };

        while let Some(word) = args.pop() {
            msg.push(' ');
            msg.push_str(word);
        }

        Box::pin(async move { Some(Arg::Msg(msg)) })
    }

    fn suggest<'a>(
        &'a self,
        sender: &pumpkin::command::CommandSender,
        _server: &'a pumpkin::server::Server,
        input: &'a str,
    ) -> SuggestResult<'a> {
        let location = if let Some(position) = sender.position()
            && let Some(world) = sender.world()
        {
            let rotation = if let Some(player) = sender.as_player() {
                let entity = player.get_entity();
                let yaw = entity.yaw.load();
                let pitch = entity.pitch.load();
                Some(Rotation::new(yaw, pitch))
            } else {
                None
            };

            Some(Location::new(
                world.uuid, position.x, position.y, position.z, rotation,
            ))
        } else {
            None
        };

        let command_name = self.command_name.clone();
        let command_sender: SimpleCommandSender = sender.into();

        Box::pin(async move {
            let (tx, rx) = oneshot::channel();
            self.command_tx
                .send(JvmCommand::GetCommandTabComplete {
                    command_sender,
                    cmd_name: command_name,
                    respond_to: tx,
                    args: input.split(' ').map(|arg| arg.to_string()).collect(),
                    location,
                })
                .await
                .unwrap();

            rx.await.unwrap()
        })
    }
}

impl Into<SimpleCommandSender> for &CommandSender {
    fn into(self) -> SimpleCommandSender {
        match self {
            CommandSender::Rcon(_mutex) => todo!(),
            CommandSender::Console => SimpleCommandSender::Console,
            CommandSender::Player(player) => {
                SimpleCommandSender::Player(player.gameprofile.id.to_string())
            }
            CommandSender::CommandBlock(_block_entity, _world) => todo!(),
        }
    }
}

impl CommandExecutor for JavaCommandExecutor {
    fn execute<'a>(
        &'a self,
        sender: &'a pumpkin::command::CommandSender,
        _server: &'a pumpkin::server::Server,
        _args: &'a pumpkin::command::args::ConsumedArgs<'a>,
    ) -> pumpkin::command::CommandResult<'a> {
        Box::pin(async move {
            let (tx, _rx) = oneshot::channel();
            self.command_tx
                .send(JvmCommand::TriggerCommand {
                    cmd_name: self.cmd_name.clone(),
                    respond_to: tx,
                    command_sender: sender.into(),
                    args: vec![self.cmd_name.clone()],
                })
                .await
                .unwrap();
            Ok(())
        })
    }
}

use std::sync::{Arc, Mutex};

use j4rs::Instance;
use pumpkin::command::CommandExecutor;
use tokio::sync::{mpsc, oneshot};

use crate::java::jvm::commands::JvmCommand;

pub struct JavaCommandExecutor {
    pub cmd_name: String,
    pub command_tx: mpsc::Sender<JvmCommand>,
    pub command: Arc<Mutex<Instance>>,
}

#[derive(Clone)]
pub enum SimpleCommandSender {
    Console,
    // UUID
    Player(String),
}

impl CommandExecutor for JavaCommandExecutor {
    fn execute<'a>(
        &'a self,
        sender: &'a pumpkin::command::CommandSender,
        server: &'a pumpkin::server::Server,
        args: &'a pumpkin::command::args::ConsumedArgs<'a>,
    ) -> pumpkin::command::CommandResult<'a> {
        Box::pin(async move {
            let (tx, rx) = oneshot::channel();
            let sender = match sender {
                pumpkin::command::CommandSender::Rcon(mutex) => todo!(),
                pumpkin::command::CommandSender::Console => SimpleCommandSender::Console,
                pumpkin::command::CommandSender::Player(player) => {
                    SimpleCommandSender::Player(player.gameprofile.id.to_string())
                }
                pumpkin::command::CommandSender::CommandBlock(block_entity, world) => todo!(),
            };
            self.command_tx
                .send(JvmCommand::TriggerCommand {
                    cmd_name: self.cmd_name.clone(),
                    respond_to: tx,
                    command_sender: sender.clone(),
                    command: self.command.clone(),
                })
                .await
                .unwrap();

            // match rx.await.unwrap().unwrap() {
            //     Event::PlayerJoinEvent(player_join_event) => *event = player_join_event,
            //     _ => unreachable!(),
            // };
            Ok(())
        })
    }
}

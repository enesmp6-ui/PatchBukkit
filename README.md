# Papkin

A plugin for [PumpkinMC](https://pumpkinmc.org/) that adds support for [PaperMC](https://papermc.io/), [Spigot](https://www.spigotmc.org/), and [Bukkit](https://dev.bukkit.org/) plugins.

## Installation

> [!IMPORTANT]
> Currently Papkin is in heavy development, as such releases are not yet available.

1. Install [java](https://docs.papermc.io/misc/java-install/)
2. Download the plugin in releases `papkin-mac.dylib`, `papkin-linux-aarch64.so`, `papkin-linux-x86.so`, or `papkin-windows-x86.dll`.
3. Add it into your PumpkinMC plugin directory (this gets created after runing PumpkinMC for the first time).
4. Run PumpkinMC again, and you should see a new directory in PumpkinMC called `papkin`. You can add any `bukkit`, `spigot`, or `paper` plugin you wish, and it will run.

## Development

If you wish to contribute to Papkin, follow the following steps:

1. Build PumpkinMC from source with the `nightly-2025-12-11` rust toolchain.
2. Clone this repository
3. `cd` into the `java` directory and run `./gradlew build`
4. `cd` into the `rust` directory and run `cargo build`
5. Copy the `target/debug/papkin` binary to the `plugins` directory in your PumpkinMC server.

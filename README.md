# PatchBukkit

A plugin for [PumpkinMC](https://pumpkinmc.org/) that adds support for [PaperMC](https://papermc.io/), [Spigot](https://www.spigotmc.org/), and [Bukkit](https://dev.bukkit.org/) plugins.

## Installation

> [!IMPORTANT]
> Currently PatchBukkit is in heavy development, as such releases are not yet available.

1. Install [java](https://docs.papermc.io/misc/java-install/)
2. Download the plugin in releases `patchbukkit-mac.dylib`, `patchbukkit-linux-aarch64.so`, `patchbukkit-linux-x86.so`, or `patchbukkit-windows-x86.dll`.
3. Add it into your PumpkinMC plugin directory (this gets created after runing PumpkinMC for the first time).
4. Run PumpkinMC again, and you should see a new directory in PumpkinMC called `patchbukkit`. You can add any `bukkit`, `spigot`, or `paper` plugin you wish, and it will run.

## Development

If you wish to contribute to PatchBukkit, follow the following steps:

1. Build PumpkinMC from source with the `nightly-2025-12-11` rust toolchain.
2. Clone this repository
3. `cd` into the `java` directory and run `./gradlew build`
4. `cd` into the `rust` directory and run `cargo build`
5. Copy the `target/debug/patchbukkit` binary to the `plugins` directory in your PumpkinMC server.

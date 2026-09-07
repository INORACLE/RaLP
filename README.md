# RaLP

RaLP is a server-side registration/login authorization plugin for Minecraft. When a player joins, they are put into
Spectator mode and receive a `/register` (new accounts) or `/login` (existing accounts) prompt. Movement and all
commands are blocked until the player authenticates.

This repository contains the source code decompiled (CFR) from the original `RaLP-Forge-1.20-0.0.1.jar`
and ported from **Forge 1.20.1** to **NeoForge 1.21.1**.

## Downloads / build

The NeoForge 1.21.1 build is produced automatically by [GitHub Actions](.github/workflows/build.yml)
on every push to `main`. Grab the artifact from the latest run, or build it yourself:

```
./gradlew build
```

The resulting jar is written to `build/libs/`. **Java 21** is required.

## Commands

| Command | Permission | Description |
| --- | --- | --- |
| `/register <password> <repeat>` | everyone | Register a new account and log in |
| `/login <password>` | everyone | Log in to an existing account |
| `/changepassword <old> <new> <repeat>` | everyone | Change your password (logs you out) |
| `/unreg <player>` | op (level 4) | Delete a player's account |
| `/ralp` / `/ralp help` | op (level 4) | Show help |
| `/ralp set max-attempts <N>` | op (level 4) | Max login attempts before blocking |
| `/ralp set block-time <sec>` | op (level 4) | Block duration after failed logins |
| `/ralp set min-length <N>` | op (level 4) | Minimum password length |
| `/ralp set language <lang>` | op (level 4) | Message language (`en ru de es fr pt tr it ko ja zh pl`) |
| `/ralp reload` | op (level 4) | Reload config from `config/ralp/config.json` |

## Configuration

- `config/ralp/config.json` - mod settings (`maxLoginAttempts`, `blockTimeSeconds`, `minPasswordLength`, `language`)
- `config/ralp/players.json` - registered players and their SHA-256 password hashes

## Porting notes (Forge 1.20.1 -> NeoForge 1.21.1)

- `MinecraftForge.EVENT_BUS` -> `NeoForge.EVENT_BUS`
- `net.minecraftforge.*` imports -> `net.neoforged.neoforge.*` / `net.neoforged.bus.api.*`
- `TickEvent.PlayerTickEvent` (with `Phase`) -> top-level `PlayerTickEvent.Pre` / `PlayerTickEvent.Post`
- SRG obfuscated method names in the decompiled code restored to their Mojang-mapped names
- Dropped the (empty) mixin config
- Mod metadata moved to `META-INF/neoforge.mods.toml`

## License

GPL-3.0 (see [LICENSE](LICENSE)).
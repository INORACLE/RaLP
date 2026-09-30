# RaLP

RaLP is a server-side registration/login authorization plugin for Minecraft. When a player joins, they are put into
Spectator mode and receive a `/register` (new accounts) or `/login` (existing accounts) prompt. Movement and all
commands are blocked until the player authenticates.

This repository contains the source code decompiled (CFR) from the original `RaLP-Forge-1.20-0.0.1.jar`
and ported from **Forge 1.20.1** to **NeoForge 1.21.1**, then from **NeoForge 1.21.1** to **Fabric 1.21.1**.

## Downloads / build

The Fabric 1.21.1 build is produced automatically by [GitHub Actions](.github/workflows/build.yml)
on every push to `main`. Grab the artifact from the latest run, or build it yourself:

```
./gradlew build
```

The resulting jar is written to `build/libs/`. **Java 21** is required.

Runtime requirements: Fabric Loader `0.16.14+`, Fabric API `0.116.17+1.21.1`. The
[Fabric Permissions API](https://github.com/FabricMC/fabric-permissions-api) (`0.3.1`) is bundled
inside the jar, so it does not need to be installed separately.

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

## Permissions

| Node | Default |
| --- | --- |
| `ralp.register` | allow |
| `ralp.login` | allow |
| `ralp.changepassword` | allow |

Op-only commands (`/unreg`, `/ralp ...`) are gated by permission level 4. The three nodes above are
resolved through the Fabric Permissions API, so any permission provider (for example LuckPerms) works
out of the box. **If no provider is installed the API returns `DEFAULT`, which RaLP treats as "allowed"**,
i.e. all players may register/log in.

## Porting notes (Forge 1.20.1 -> NeoForge 1.21.1 -> Fabric 1.21.1)

- `net.minecraftforge.*` / `net.neoforged.*` imports -> `net.fabricmc.fabric.api.*` callbacks
- `@Mod` constructor + `NeoForge.EVENT_BUS` -> `ModInitializer.onInitialize()`
- `RegisterCommandsEvent` -> `CommandRegistrationCallback.EVENT` (fabric-command-api-v2)
- `PlayerEvent.PlayerLoggedInEvent` / `PlayerLoggedOutEvent` -> `ServerPlayConnectionEvents.JOIN` / `.DISCONNECT`
- `TickEvent.PlayerTickEvent` -> `ServerTickEvents.END_SERVER_TICK` (no per-player tick event exists in this API version, so the player list is iterated once per server tick)
- `CommandEvent` -> `CommandManagerMixin` injecting into `CommandManager.execute(ParseResults, String)` and cancelling it for unauthenticated players
- Mojang-mapped names -> Yarn names (`Component` -> `Text`, `ServerPlayer` -> `ServerPlayerEntity`, `Vec3` -> `Vec3d`, `getUUID()` -> `getUuid()`, ...)
- `net.minecraftforge.common.FMLPaths.CONFIGDIR` -> `FabricLoader.getInstance().getConfigDir()`
- Mod metadata moved from `META-INF/neoforge.mods.toml` to `fabric.mod.json` (+ `ralp.mixins.json`)
- Loom remaps the mixin references to intermediary at build time, so no refmap file is shipped

## License

GPL-3.0 (see [LICENSE](LICENSE)).
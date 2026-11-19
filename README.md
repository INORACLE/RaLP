# RaLP

RaLP is a server-side registration/login authorization plugin for Minecraft. When a player joins, they are put into
Spectator mode and receive a `/register` (new accounts) or `/login` (existing accounts) prompt. Movement and all
commands are blocked until the player authenticates.

This repository contains the source code decompiled (CFR) from the original `RaLP-Forge-1.20-0.0.1.jar`
and ported from **Forge 1.20.1** to **NeoForge 1.21.1** (`neoforge/`, Mojang mappings) and to
**Fabric 1.21.1** (repository root, Yarn mappings). Both loaders are maintained side by side.

## Downloads / build

Both loader builds are produced automatically by [GitHub Actions](.github/workflows/build.yml) on every
push to `main`. Grab the artifacts from the latest run, or build them yourself (Java 21 required):

| Loader | Target | Gradle project | Output |
| --- | --- | --- | --- |
| NeoForge | 21.1.251 (MC 1.21.1) | `neoforge/` | `neoforge/build/libs/RaLP-NeoForge-1.21.1-<version>.jar` |
| Fabric | 1.21.1 | repository root | `build/libs/RaLP-Fabric-1.21.1-<version>.jar` |

```
./gradlew build              # Fabric
./gradlew -p neoforge build  # NeoForge
```

Runtime requirements: Fabric Loader `0.16.14+`, Fabric API `0.116.17+1.21.1`. The
[Fabric Permissions API](https://github.com/FabricMC/fabric-permissions-api) (`0.3.1`) is bundled
inside the jar, so it does not need to be installed separately.

## Fixed in 0.0.3 - players stuck in mid-air after login

**Symptom:** after joining a NeoForge 1.21.1 server and running `/login` or `/register`, a player could only
move horizontally. They hovered in mid-air and could neither descend nor jump.

**Root cause:** unauthenticated players are held in `SPECTATOR`, which grants flight. NeoForge deliberately
preserves `abilities.flying` when the game mode changes (`ServerPlayerGameMode#setGameModeForPlayer`:
`flying = wasFlying || newFlying`), unlike vanilla. Switching straight back to survival therefore emitted
one or more ability packets with `flying = true` while `mayfly = false`. The client enters a flying state it
is not permitted to leave, so it keeps hovering and never touches the ground (see also
[NeoForge #1454](https://github.com/neoforged/NeoForge/issues/1454)).

**Fix:** `AuthSupport#completeLogin` clears the flight flags *before* the mode change (so NeoForge has
nothing to carry over), re-asserts a clean survival ability set afterwards, forces an ability sync, and
resets velocity and fall distance. Verified on a local NeoForge 21.1.251 server with packet capture: the
old jar emitted `flying=1 mayfly=0` right after authentication, the fixed jar goes straight to
`flying=0 mayfly=0`, and jump tests return to the ground normally. The same hardening is applied to the
Fabric build.


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
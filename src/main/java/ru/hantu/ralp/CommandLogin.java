package ru.hantu.ralp;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

public class CommandLogin {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("login")
                .requires(src -> Perms.canUse(src, Perms.LOGIN))
                .then(CommandManager.argument("password", StringArgumentType.word())
                    .executes(CommandLogin::execute))
        );
    }

    private static int execute(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerCommandSource source = ctx.getSource();
        if (source.getPlayer() == null) {
            source.sendError(Messages.get("errors.player-only"));
            return 0;
        }
        ServerPlayerEntity player = source.getPlayerOrThrow();
        String password = StringArgumentType.getString(ctx, "password");
        if (AuthManager.isAuthenticated(player)) {
            player.sendMessage(Messages.get("login.already"), false);
            return 0;
        }
        if (AuthManager.isBlocked(player)) {
            long sec = AuthManager.getRemainingSeconds(player);
            String msg = Messages.get("login.blocked-time").getString().replace("{time}", String.valueOf(sec));
            player.sendMessage(Text.literal(msg), false);
            return 0;
        }
        String hash = PlayerDataStorage.getPasswordHash(player.getUuid());
        if (hash == null) {
            player.sendMessage(Messages.get("login.not-registered"), false);
            return 0;
        }
        if (PasswordManager.verify(password, hash)) {
            AuthManager.setAuthenticated(player, true);
            AuthManager.clear(player);
            player.changeGameMode(GameMode.SURVIVAL);
            player.sendMessage(Messages.get("login.success"), false);
            return 1;
        }
        AuthManager.recordFailed(player);
        if (AuthManager.isBlocked(player)) {
            player.sendMessage(Messages.get("login.blocked"), false);
        } else {
            player.sendMessage(Messages.get("login.wrong"), false);
        }
        return 0;
    }
}
package ru.hantu.ralp;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

public class CommandLogin {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("login")
                .then(Commands.argument("password", StringArgumentType.word())
                    .executes(CommandLogin::execute))
        );
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSourceStack source = ctx.getSource();
        if (!source.isPlayer()) {
            source.sendFailure(Messages.get("errors.player-only"));
            return 0;
        }
        ServerPlayer player = source.getPlayerOrException();
        String password = StringArgumentType.getString(ctx, "password");
        if (AuthManager.isAuthenticated(player)) {
            player.sendSystemMessage(Messages.get("login.already"));
            return 0;
        }
        if (AuthManager.isBlocked(player)) {
            long sec = AuthManager.getRemainingSeconds(player);
            String msg = Messages.get("login.blocked-time").getString().replace("{time}", String.valueOf(sec));
            player.sendSystemMessage(Component.literal(msg));
            return 0;
        }
        String hash = PlayerDataStorage.getPasswordHash(player.getUUID());
        if (hash == null) {
            player.sendSystemMessage(Messages.get("login.not-registered"));
            return 0;
        }
        if (PasswordManager.verify(password, hash)) {
            AuthManager.setAuthenticated(player, true);
            AuthManager.clear(player);
            player.setGameMode(GameType.SURVIVAL);
            player.sendSystemMessage(Messages.get("login.success"));
            return 1;
        }
        AuthManager.recordFailed(player);
        if (AuthManager.isBlocked(player)) {
            player.sendSystemMessage(Messages.get("login.blocked"));
        } else {
            player.sendSystemMessage(Messages.get("login.wrong"));
        }
        return 0;
    }
}
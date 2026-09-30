package ru.hantu.ralp;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class CommandChangePassword {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("changepassword")
                .requires(src -> Perms.canUse(src, Perms.CHANGEPASSWORD))
                .then(CommandManager.argument("old", StringArgumentType.word())
                    .then(CommandManager.argument("new", StringArgumentType.word())
                        .then(CommandManager.argument("confirm", StringArgumentType.word())
                            .executes(CommandChangePassword::execute))))
        );
    }

    private static int execute(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerCommandSource source = ctx.getSource();
        if (source.getPlayer() == null) {
            source.sendError(Messages.get("errors.player-only"));
            return 0;
        }
        ServerPlayerEntity player = source.getPlayerOrThrow();
        String oldPass = StringArgumentType.getString(ctx, "old");
        String newPass = StringArgumentType.getString(ctx, "new");
        String confirm = StringArgumentType.getString(ctx, "confirm");
        if (!newPass.equals(confirm)) {
            player.sendMessage(Messages.get("register.passwords-not-match"), false);
            return 0;
        }
        if (newPass.length() < ConfigManager.minPasswordLength) {
            player.sendMessage(Messages.get("register.too-short"), false);
            return 0;
        }
        String hash = PlayerDataStorage.getPasswordHash(player.getUuid());
        if (hash == null || !PasswordManager.verify(oldPass, hash)) {
            player.sendMessage(Messages.get("login.wrong"), false);
            return 0;
        }
        PlayerDataStorage.updatePassword(player, PasswordManager.hash(newPass));
        AuthManager.setAuthenticated(player, false);
        player.sendMessage(Messages.get("changepassword.success"), false);
        return 1;
    }
}
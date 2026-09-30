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

public class CommandRegister {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("register")
                .requires(src -> Perms.canUse(src, Perms.REGISTER))
                .then(CommandManager.argument("password", StringArgumentType.word())
                    .then(CommandManager.argument("confirm", StringArgumentType.word())
                        .executes(CommandRegister::execute)))
        );
    }

    private static int execute(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        ServerCommandSource source = ctx.getSource();
        if (source.getPlayer() == null) {
            source.sendError(Messages.get("errors.player-only"));
            return 0;
        }
        ServerPlayerEntity player = source.getPlayerOrThrow();
        player.sendMessage(Text.literal("\u00a7b[DEBUG] Register executed"), false);
        String pass1 = StringArgumentType.getString(ctx, "password");
        String pass2 = StringArgumentType.getString(ctx, "confirm");
        if (!pass1.equals(pass2)) {
            player.sendMessage(Messages.get("register.passwords-not-match"), false);
            return 0;
        }
        if (pass1.length() < ConfigManager.minPasswordLength) {
            player.sendMessage(Messages.get("register.too-short"), false);
            return 0;
        }
        if (PlayerDataStorage.isRegistered(player.getUuid())) {
            player.sendMessage(Messages.get("register.already"), false);
            return 0;
        }
        String hash;
        try {
            hash = PasswordManager.hash(pass1);
        } catch (Exception e) {
            player.sendMessage(Text.literal("\u00a7c[ERROR] Password hashing failed!"), false);
            e.printStackTrace();
            return 0;
        }
        player.sendMessage(Text.literal("\u00a7b[DEBUG] Register executed 2"), false);
        PlayerDataStorage.register(player, hash);
        AuthManager.setAuthenticated(player, true);
        player.changeGameMode(GameMode.SURVIVAL);
        player.sendMessage(Messages.get("register.success"), false);
        return 1;
    }
}
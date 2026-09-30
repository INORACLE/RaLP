package ru.hantu.ralp;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.UUID;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class CommandUnregister {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("unreg")
                .requires(src -> src.hasPermissionLevel(4))
                .then(CommandManager.argument("player", EntityArgumentType.player())
                    .executes(CommandUnregister::execute))
        );
    }

    private static int execute(CommandContext<ServerCommandSource> ctx) {
        ServerPlayerEntity target;
        try {
            target = EntityArgumentType.getPlayer(ctx, "player");
        } catch (CommandSyntaxException e) {
            ctx.getSource().sendError(Text.literal("\u00a7cPlayer not found!"));
            return 0;
        }
        UUID uuid = target.getUuid();
        if (!PlayerDataStorage.isRegistered(uuid)) {
            ctx.getSource().sendError(Messages.get("unreg.not-registered"));
            return 0;
        }
        PlayerDataStorage.unregister(uuid);
        AuthManager.setAuthenticated(target, false);
        if (target.isAlive()) {
            target.sendMessage(Text.literal("\u00a7cYour account has been unregistered by admin."), false);
        }
        String msg = Messages.get("unreg.success").getString().replace("{player}", target.getDisplayName().getString());
        ctx.getSource().sendFeedback(() -> Text.literal(msg), true);
        return 1;
    }
}
package ru.hantu.ralp.mixin;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.ParsedCommandNode;
import java.util.List;
import java.util.Set;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.hantu.ralp.AuthManager;
import ru.hantu.ralp.Messages;

@Mixin(CommandManager.class)
public abstract class CommandManagerMixin {
    private static final Set<String> ALLOWED_COMMANDS = Set.of("login", "register", "changepassword", "help");

    @Inject(
        method = "execute(Lcom/mojang/brigadier/ParseResults;Ljava/lang/String;)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void ralp$blockUnauthenticatedCommands(
        ParseResults<ServerCommandSource> results,
        String command,
        CallbackInfo info
    ) {
        if (results.getContext() == null) {
            return;
        }
        ServerCommandSource source = results.getContext().getSource();
        if (!(source.getEntity() instanceof ServerPlayerEntity player)) {
            return;
        }
        if (AuthManager.isAuthenticated(player)) {
            return;
        }
        List<ParsedCommandNode<ServerCommandSource>> nodes = results.getContext().getNodes();
        if (nodes.isEmpty() || !ALLOWED_COMMANDS.contains(nodes.get(0).getNode().getName())) {
            player.sendMessage(Messages.get("errors.not-logged-in"), false);
            info.cancel();
        }
    }
}
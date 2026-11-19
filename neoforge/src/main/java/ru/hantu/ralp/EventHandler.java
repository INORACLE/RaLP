package ru.hantu.ralp;

import com.mojang.brigadier.context.ParsedCommandNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.CommandEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class EventHandler {
    private static final Map<UUID, Vec3> spawnPositions = new HashMap<>();

    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (!(player instanceof ServerPlayer)) {
            return;
        }
        ServerPlayer serverPlayer = (ServerPlayer) player;
        AuthManager.setAuthenticated(serverPlayer, false);
        serverPlayer.setGameMode(GameType.SPECTATOR);
        spawnPositions.put(serverPlayer.getUUID(), serverPlayer.position());
        if (PlayerDataStorage.isRegistered(serverPlayer.getUUID())) {
            serverPlayer.sendSystemMessage(Messages.get("login.usage"));
        } else {
            serverPlayer.sendSystemMessage(Messages.get("register.usage"));
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if (!(player instanceof ServerPlayer)) {
            return;
        }
        ServerPlayer serverPlayer = (ServerPlayer) player;
        if (AuthManager.isAuthenticated(serverPlayer)) {
            return;
        }
        Vec3 spawn = spawnPositions.get(serverPlayer.getUUID());
        if (spawn == null) {
            return;
        }
        if (serverPlayer.getX() != spawn.x || serverPlayer.getY() != spawn.y || serverPlayer.getZ() != spawn.z) {
            serverPlayer.teleportTo(serverPlayer.serverLevel(), spawn.x, spawn.y, spawn.z, serverPlayer.getYRot(), serverPlayer.getXRot());
        }
    }

    @SubscribeEvent
    public void onCommand(CommandEvent event) {
        CommandSourceStack source = event.getParseResults().getContext().getSource();
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        if (AuthManager.isAuthenticated(player)) {
            return;
        }
        List<ParsedCommandNode<CommandSourceStack>> nodes = event.getParseResults().getContext().getNodes();
        if (nodes.isEmpty()) {
            event.setCanceled(true);
            player.sendSystemMessage(Messages.get("errors.not-logged-in"));
            return;
        }
        String commandName = nodes.get(0).getNode().getName();
        if ("login".equals(commandName) || "register".equals(commandName) || "changepassword".equals(commandName) || "help".equals(commandName)) {
            return;
        }
        event.setCanceled(true);
        player.sendSystemMessage(Messages.get("errors.not-logged-in"));
    }
}
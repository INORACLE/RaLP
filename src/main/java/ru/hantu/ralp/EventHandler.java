package ru.hantu.ralp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

public class EventHandler {
    private static final Map<UUID, Vec3d> spawnPositions = new HashMap<>();

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> onJoin(handler.getPlayer()));
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> onDisconnect(handler.getPlayer()));
        ServerTickEvents.END_SERVER_TICK.register(EventHandler::onPlayerTick);
    }

    private static void onJoin(ServerPlayerEntity player) {
        AuthManager.setAuthenticated(player, false);
        player.changeGameMode(GameMode.SPECTATOR);
        spawnPositions.put(player.getUuid(), player.getPos());
        if (PlayerDataStorage.isRegistered(player.getUuid())) {
            player.sendMessage(Messages.get("login.usage"), false);
        } else {
            player.sendMessage(Messages.get("register.usage"), false);
        }
    }

    private static void onDisconnect(ServerPlayerEntity player) {
        spawnPositions.remove(player.getUuid());
        AuthManager.clear(player);
    }

    private static void onPlayerTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            if (AuthManager.isAuthenticated(player)) {
                continue;
            }
            Vec3d spawn = spawnPositions.get(player.getUuid());
            if (spawn == null) {
                continue;
            }
            if (player.getX() != spawn.x || player.getY() != spawn.y || player.getZ() != spawn.z) {
                player.teleport(player.getServerWorld(), spawn.x, spawn.y, spawn.z, player.getYaw(), player.getPitch());
            }
        }
    }
}
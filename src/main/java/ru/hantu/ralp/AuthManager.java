package ru.hantu.ralp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.network.ServerPlayerEntity;

public class AuthManager {
    private static final Map<UUID, Boolean> authenticated = new HashMap<>();
    private static final Map<UUID, Integer> attempts = new HashMap<>();
    private static final Map<UUID, Long> blockedUntil = new HashMap<>();

    public static boolean isAuthenticated(ServerPlayerEntity player) {
        return authenticated.getOrDefault(player.getUuid(), false);
    }

    public static void setAuthenticated(ServerPlayerEntity player, boolean auth) {
        authenticated.put(player.getUuid(), auth);
    }

    public static boolean isBlocked(ServerPlayerEntity player) {
        Long until = blockedUntil.get(player.getUuid());
        if (until != null && System.currentTimeMillis() < until) {
            return true;
        }
        blockedUntil.remove(player.getUuid());
        attempts.remove(player.getUuid());
        return false;
    }

    public static void recordFailed(ServerPlayerEntity player) {
        if (isBlocked(player)) {
            return;
        }
        UUID uuid = player.getUuid();
        int count = attempts.getOrDefault(uuid, 0) + 1;
        attempts.put(uuid, count);
        if (count >= ConfigManager.maxLoginAttempts) {
            blockedUntil.put(uuid, System.currentTimeMillis() + ConfigManager.blockTimeSeconds * 1000L);
            attempts.remove(uuid);
        }
    }

    public static long getRemainingSeconds(ServerPlayerEntity player) {
        Long until = blockedUntil.get(player.getUuid());
        return until != null ? Math.max(0L, (until - System.currentTimeMillis()) / 1000L) : 0L;
    }

    public static void clear(ServerPlayerEntity player) {
        attempts.remove(player.getUuid());
        blockedUntil.remove(player.getUuid());
    }
}
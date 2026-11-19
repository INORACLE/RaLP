package ru.hantu.ralp;

import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

/**
 * Shared helper that finishes a successful login/registration.
 *
 * <p>Players are held in {@link GameMode#SPECTATOR} until they authenticate. Spectator
 * mode grants flight, and NeoForge (unlike vanilla) preserves {@code abilities.flying}
 * when the game mode changes. If we simply switch back to survival, the server can be
 * left in a state where {@code flying == true} but {@code allowFlying == false}; that
 * inconsistent state is synced to the client, which then hovers mid-air and cannot
 * descend or jump. Clearing the flight flag <em>before</em> the mode change prevents
 * NeoForge's {@code setGameModeForPlayer} from carrying it over.</p>
 */
public final class AuthSupport {
    private AuthSupport() {
    }

    public static void completeLogin(ServerPlayerEntity player) {
        // Clear flight before the game-mode change so it cannot be preserved.
        resetAbilities(player.getAbilities());

        player.changeGameMode(GameMode.SURVIVAL);

        // Re-assert a clean survival state and force it to the client.
        resetAbilities(player.getAbilities());
        player.sendAbilitiesUpdate();

        // Drop any motion/fall state accumulated while frozen in spectator.
        player.setVelocity(Vec3d.ZERO);
        player.fallDistance = 0.0F;
    }

    private static void resetAbilities(PlayerAbilities abilities) {
        abilities.flying = false;
        abilities.allowFlying = false;
        abilities.invulnerable = false;
        abilities.creativeMode = false;
    }
}

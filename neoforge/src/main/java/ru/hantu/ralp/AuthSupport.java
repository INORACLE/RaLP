package ru.hantu.ralp;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;

/**
 * Shared helper that finishes a successful login/registration.
 *
 * <p>Players are held in {@link GameType#SPECTATOR} until they authenticate. Spectator
 * mode grants flight, and NeoForge (unlike vanilla) deliberately preserves
 * {@code abilities.flying} across game-mode changes
 * ({@code ServerPlayerGameMode#setGameModeForPlayer}: {@code flying = wasFlying || newFlying}).
 * Switching straight back to survival therefore produced one or more ability packets with
 * {@code flying == true} while {@code mayfly == false} - the client enters a flying state that
 * it is not allowed to leave, so the player hovers in mid-air and cannot descend or jump.
 *
 * <p>Clearing the flight flag <em>before</em> the mode change prevents NeoForge from carrying
 * it over. The post-change reset and explicit ability sync are belt-and-braces measures for
 * players whose state was already corrupted (for example a re-login during the broken window).
 */
public final class AuthSupport {
    private AuthSupport() {
    }

    public static void completeLogin(ServerPlayer player) {
        // Clear flight before the game-mode change so it cannot be preserved.
        resetAbilities(player.getAbilities());

        // False if the player already is in survival (e.g. repeated login attempt).
        player.setGameMode(GameType.SURVIVAL);

        // Re-assert a clean survival state and force it to the client.
        resetAbilities(player.getAbilities());
        player.onUpdateAbilities();

        // Drop any motion/fall state accumulated while frozen in spectator.
        player.setDeltaMovement(Vec3.ZERO);
        player.resetFallDistance();
    }

    @SuppressWarnings("deprecation") // Abilities#mayfly is deprecated in NeoForge but still synced.
    private static void resetAbilities(Abilities abilities) {
        abilities.flying = false;
        abilities.mayfly = false;
        abilities.invulnerable = false;
        abilities.instabuild = false;
        abilities.mayBuild = true;
    }
}

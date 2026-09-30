package ru.hantu.ralp;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class Perms {
    public static final String REGISTER = "ralp.register";
    public static final String LOGIN = "ralp.login";
    public static final String CHANGEPASSWORD = "ralp.changepassword";

    public static boolean canUse(ServerCommandSource source, String permission) {
        ServerPlayerEntity player = source.getPlayer();
        return player == null || Permissions.check(player, permission, true);
    }
}
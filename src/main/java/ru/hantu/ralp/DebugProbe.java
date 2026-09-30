package ru.hantu.ralp;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugProbe {
    private static final Logger LOGGER = LoggerFactory.getLogger("RaLPDBG");
    private static final UUID PROBE = UUID.nameUUIDFromBytes("RaLP-permission-probe".getBytes(StandardCharsets.UTF_8));

    public static void install() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            for (String permission : new String[]{Perms.REGISTER, Perms.LOGIN, Perms.CHANGEPASSWORD}) {
                Permissions.getPermissionValue(PROBE, permission).whenComplete((value, error) ->
                    LOGGER.info("{} = {}", permission, error != null ? "error: " + error : value));
            }
        });
    }
}
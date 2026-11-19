package ru.hantu.ralp;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

public class Main implements ModInitializer {
    public static final String MOD_ID = "ralp";

    @Override
    public void onInitialize() {
        PlayerDataStorage.init();
        ConfigManager.init();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            CommandDispatcher<ServerCommandSource> commandDispatcher = dispatcher;
            CommandRegister.register(commandDispatcher);
            CommandLogin.register(commandDispatcher);
            CommandChangePassword.register(commandDispatcher);
            CommandUnregister.register(commandDispatcher);
            CommandRalp.register(commandDispatcher);
        });

        EventHandler.register();
    }
}
package ru.hantu.ralp;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod("ralp")
public class Main {
    public Main() {
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new EventHandler());
        PlayerDataStorage.init();
        ConfigManager.init();
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        CommandRegister.register(dispatcher);
        CommandLogin.register(dispatcher);
        CommandChangePassword.register(dispatcher);
        CommandUnregister.register(dispatcher);
        CommandRalp.register(dispatcher);
    }
}
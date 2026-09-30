package ru.hantu.ralp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDataStorage {
    private static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir().resolve("ralp");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type MAP_TYPE = new TypeToken<Map<UUID, PlayerData>>() {}.getType();
    private static Map<UUID, PlayerData> data = new HashMap<>();

    public static void init() {
        try {
            Files.createDirectories(CONFIG_DIR);
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        Path file = CONFIG_DIR.resolve("players.json");
        if (Files.exists(file)) {
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                data = GSON.fromJson(reader, MAP_TYPE);
                if (data == null) {
                    data = new HashMap<>();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        Path file = CONFIG_DIR.resolve("players.json");
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isRegistered(UUID uuid) {
        return data.containsKey(uuid);
    }

    public static void register(ServerPlayerEntity player, String hash) {
        data.put(player.getUuid(), new PlayerData(hash, player.getGameProfile().getName()));
        save();
    }

    public static String getPasswordHash(UUID uuid) {
        PlayerData d = data.get(uuid);
        return d != null ? d.passwordHash : null;
    }

    public static void updatePassword(ServerPlayerEntity player, String newHash) {
        PlayerData d = data.get(player.getUuid());
        if (d != null) {
            d.passwordHash = newHash;
            save();
        }
    }

    public static void unregister(UUID uuid) {
        data.remove(uuid);
        save();
    }

    public static class PlayerData {
        public String passwordHash;
        public String name;

        public PlayerData(String passwordHash, String name) {
            this.passwordHash = passwordHash;
            this.name = name;
        }
    }
}
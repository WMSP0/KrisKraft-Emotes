package com.wmsp0.kriskraftemotes;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EmoteManager {

    private static EmoteManager instance;

    public static EmoteManager get() {
        if (instance == null) {
            instance = new EmoteManager();
        }
        return instance;
    }

    private final File file;
    private FileConfiguration config;

    private final HashMap<String, String> emotes;

    EmoteManager() {
        emotes = new HashMap<>();
        file = new File(KrisKraftEmotes.getInstance().getDataFolder(), "config.yml");
    }

    public void load() {
        config = YamlConfiguration.loadConfiguration(file);

        // Grab emotes from config.yml
        ConfigurationSection emoteSection = config.getConfigurationSection("Emotes");

        // If section is found, return
        if (emoteSection == null) return;

        // Loop through all the defined emotes
        for (String emote : emoteSection.getKeys(false)) {
            emotes.put(emote, config.getString("Emotes." + emote));
        }
    }

    public void unload() {
        emotes.clear();
    }

    public void reload() {
        emotes.clear();
        load();
    }

    public String formatMessage(String message) {
        String[] messageArray = message.split(" ");

        for (int i = 0; i < messageArray.length; i++){
            String currentPart = messageArray[i];
            if (emotes.containsKey(currentPart)) {
                messageArray[i] = emotes.get(currentPart);
            }
        }
        return String.join(" ", messageArray);
    }

    public void displayEmotes(Player player) {
        for (Map.Entry<String, String> entry : emotes.entrySet()) {
            player.sendMessage(ChatColor.GOLD + entry.getKey() + ChatColor.WHITE + " - " + entry.getValue());
        }
    }
}

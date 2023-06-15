package com.wmsp0.kriskraftemotes;

import com.wmsp0.kriskraftemotes.commands.CommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class KrisKraftEmotes extends JavaPlugin {

    private static KrisKraftEmotes instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Save default config
        saveResource("config.yml", false);

        // Load emotes from config.yml
        EmoteManager.get().load();

        // Register listener
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        // Register command
        getCommand("emotes").setExecutor(new CommandHandler());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        EmoteManager.get().unload();
    }

    public static KrisKraftEmotes getInstance() {
        return instance;
    }
}

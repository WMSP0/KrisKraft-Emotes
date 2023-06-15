package com.wmsp0.kriskraftemotes.commands.subcommands;

import com.wmsp0.kriskraftemotes.EmoteManager;
import com.wmsp0.kriskraftemotes.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    @Override
    public String getDescription() {
        return "Reloads the config.yml to update any changes made";
    }

    @Override
    public String getPermission() {
        return "emotes.reload";
    }

    @Override
    public String getSyntax() {
        return "/emotes reload";
    }

    @Override
    public void execute(Player player, String[] args) {
        EmoteManager.get().reload();
        player.sendMessage(ChatColor.GREEN + "Config reloaded!");
    }
}

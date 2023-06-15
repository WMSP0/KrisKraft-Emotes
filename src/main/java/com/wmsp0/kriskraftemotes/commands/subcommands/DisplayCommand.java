package com.wmsp0.kriskraftemotes.commands.subcommands;

import com.wmsp0.kriskraftemotes.EmoteManager;
import com.wmsp0.kriskraftemotes.commands.SubCommand;
import org.bukkit.entity.Player;

public class DisplayCommand extends SubCommand {

    @Override
    public String getDescription() {
        return "Displays all the available emotes";
    }

    @Override
    public String getPermission() {
        return "emotes.display";
    }

    @Override
    public String getSyntax() {
        return "/emotes display";
    }

    @Override
    public void execute(Player player, String[] args) {
        EmoteManager.get().displayEmotes(player);
    }
}

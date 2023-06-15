package com.wmsp0.kriskraftemotes.commands;

import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String getDescription();

    public abstract String getPermission();

    public abstract String getSyntax();

    public abstract void execute(Player player, String[] args);

}

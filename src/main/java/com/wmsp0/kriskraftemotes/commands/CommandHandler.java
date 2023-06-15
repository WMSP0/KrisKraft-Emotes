package com.wmsp0.kriskraftemotes.commands;

import com.wmsp0.kriskraftemotes.commands.subcommands.DisplayCommand;
import com.wmsp0.kriskraftemotes.commands.subcommands.ReloadCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandHandler implements CommandExecutor {

    // All available sub-commands
    private final HashMap<String, SubCommand> subCommands = new HashMap<>();

    public CommandHandler() {
        // Add sub-commands
        subCommands.put("display", new DisplayCommand());
        subCommands.put("reload", new ReloadCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Make sure the sender has the correct permissions
        if (!sender.hasPermission("emotes.use")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return false;
        }

        // Make sure the sender is a player, not console
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players may execute this command");
            return false;
        }

        // Player
        Player player = (Player) sender;

        // Make sure the argument length is greater than 0
        if (args.length == 0) {
            sendHelpMenu(player);
            return false;
        }

        // User typed argument
        String subCommandName = args[0];

        // Get sub-command from HashMap
        SubCommand subCommand = subCommands.get(subCommandName.toLowerCase());

        // Make sure sub-command is valid
        if (subCommand == null) {
            sendHelpMenu(player);
            return false;
        }

        // Check permissions (if it's null, then no permission is needed)
        if (subCommand.getPermission() == null || player.hasPermission(subCommand.getPermission())) {
            // Execute sub-command
            subCommand.execute(player, args);
        } else {
            // Send permission error message
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
        }
        return true;
    }

    // Send the help menu to the player
    private void sendHelpMenu(Player player) {
        player.sendMessage("--------------------------------");
        subCommands.forEach((key, subCommand) -> {
            // Only send the commands they have access to
            if (player.hasPermission(subCommand.getPermission())) {
                player.sendMessage(ChatColor.GREEN + subCommand.getSyntax() + ChatColor.WHITE + " - " + subCommand.getDescription());
            }
        });
        player.sendMessage("--------------------------------");
    }
}

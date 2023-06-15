package com.wmsp0.kriskraftemotes;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setMessage(EmoteManager.get().formatMessage(event.getMessage()));
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        for (int i = 0; i < event.getLines().length; i++) {
            String line = ChatColor.WHITE + EmoteManager.get().formatMessage(event.getLine(i)) + ChatColor.BLACK;
            event.setLine(i, line);
        }
    }

    @EventHandler
    public void onPrepareAnvilEvent(PrepareAnvilEvent event) {
        if (event.getResult() == null) return;
        if (!event.getResult().hasItemMeta()) return;
        if (event.getInventory().getRenameText().isEmpty()) return;

        ItemStack result = event.getResult();
        ItemMeta resultMeta = result.getItemMeta();
        resultMeta.setDisplayName(EmoteManager.get().formatMessage(event.getInventory().getRenameText()));
        result.setItemMeta(resultMeta);
    }
}

package me.nate.main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class DragonEggListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.ENDER_CHEST) {
            if (playerHasDragonEgg(event.getPlayer())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("You cannot open an ender chest while carrying a dragon egg!");
            }
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().toLowerCase();
        if (command.startsWith("/ender") || command.startsWith("/ec")) {
            if (playerHasDragonEgg(event.getPlayer())) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("You cannot use this command while carrying a dragon egg!");
            }
        }
    }

    private boolean playerHasDragonEgg(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.DRAGON_EGG) {
                return true;
            }
        }
        return false;
    }
}

package com.redeseason.spawnershop.listener;

import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import store.seasonlabs.api.libraries.formatter.NumberLibrary;

public class CheckListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    void interactCheck(PlayerInteractEvent event) {

        SpawnerShopUser user = UserCacheHandler.getHandler().getUser(event.getPlayer().getName());
        ItemStack item = event.getItem();

        if (event.getAction() != Action.RIGHT_CLICK_AIR
                && event.getAction() != Action.RIGHT_CLICK_BLOCK
                || item.getType() == Material.AIR) return;

        NBTItem nbtItem = new NBTItem(item);

        if(!nbtItem.hasKey("value")) return;

        event.setCancelled(true);
        event.getPlayer().setItemInHand(null);

        Double value = nbtItem.getDouble("value") * item.getAmount();

        if(event.getPlayer().isSneaking()) {
            val contents = event.getPlayer().getInventory().getContents();
            for (int i = 0; i < contents.length; i++) {
                val content = contents[i];
                if (content == null || content.getType() == Material.AIR) continue;

                val contentNbt = new NBTItem(content);
                if (!contentNbt.hasKey("value")) continue;

                value += contentNbt.getDouble("value") * content.getAmount();
                contents[i] = null;


            }

        }

        event.getPlayer().sendMessage("§a + " + NumberLibrary.format(value));

    }

}

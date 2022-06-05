package com.redeseason.spawnershop.util;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import store.seasonlabs.api.libraries.inventory.ItemLibrary;

public class CheckUtil {

    public static ItemStack createItem(Double value) {

        ItemStack item = new ItemLibrary(Material.SULPHUR)
                .name("§aCheque de Limites")
                .lore(
                        "§fQuantia: §7" + value,
                        "",
                        "§7Clique para utilizar."
                )
                .build();

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setDouble("value", value);

        return nbtItem.getItem();

    }

}

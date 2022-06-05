package com.redeseason.spawnershop.data;

import com.redeseason.spawnershop.util.EntityUtil;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import store.seasonlabs.api.libraries.formatter.NumberLibrary;
import store.seasonlabs.api.libraries.inventory.ItemLibrary;

@Builder
@Data
public class Spawner {

    private String name;
    private Double value;

    public static ItemStack toItemStack(Spawner spawner, SpawnerShopUser user) {

        Double buyMax = user.getLimit() / spawner.getValue();

        return new ItemLibrary(Material.SKULL_ITEM)
                .owner("MHF_" + spawner.getName())
                .data(3)
                .name("§aGerador de " + EntityUtil.valueOf(spawner.getName()).getName())
                .lore(
                        "§7Coloque este gerador em sua plot",
                        "§7para gerar mobs!",
                        "",
                        "§f Preço: §7" + NumberLibrary.format(spawner.getValue()),
                        "§f Você pode comprar §7" + NumberLibrary.format(buyMax) + "§f geradores.",
                        "",
                        "§8➟ §fBotão direito: §7comprar 1.",
                        "§8➟ §fBotão direito: §7escolher."
                )
                .build();


    }

}

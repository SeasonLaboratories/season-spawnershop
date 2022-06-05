package com.redeseason.spawnershop.ranking.view;

import com.redeseason.spawnershop.api.LimitAPI;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import me.saiintbrisson.minecraft.PaginatedView;
import me.saiintbrisson.minecraft.PaginatedViewSlotContext;
import me.saiintbrisson.minecraft.ViewContext;
import me.saiintbrisson.minecraft.ViewItem;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import store.seasonlabs.api.libraries.formatter.NumberLibrary;
import store.seasonlabs.api.libraries.inventory.ItemLibrary;

public class LimitRankingView extends PaginatedView<SpawnerShopUser> {

    public LimitRankingView() {

        super(4, "Ranking de Limite");
        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XXXXXXXXX"
        );

        setCancelOnClick(true);

    }

    @Override
    protected void onRender(@NotNull ViewContext context) {
        setSource(LimitAPI.getInstance().getRankingUsers());

        if(LimitAPI.getInstance().getRankingUsers().isEmpty()) context.slot(13).withItem(new ItemLibrary(Material.SULPHUR)
                .name("§cRanking atualizando...")
                .lore("§7O ranking está atualizando.")
                .build());

    }

    @Override
    protected void onItemRender(PaginatedViewSlotContext<SpawnerShopUser> render, ViewItem item, SpawnerShopUser value) {

        item.withItem(new ItemLibrary(Material.SKULL_ITEM)
                .name("§a" + value.getName() + "")
                .owner(value.getName())
                .data(3)
                .lore(
                        "",
                        "§f  Limite: §7" + NumberLibrary.format(value.getLimit()),
                        ""
                )
                .build()
        );

    }
}

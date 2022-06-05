package com.redeseason.spawnershop.view;

import com.redeseason.spawnershop.data.Spawner;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import me.saiintbrisson.minecraft.PaginatedView;
import me.saiintbrisson.minecraft.PaginatedViewSlotContext;
import me.saiintbrisson.minecraft.ViewItem;

public class SpawnerShopView extends PaginatedView<Spawner> {

    public SpawnerShopView() {
        super(6, "Loja de Geradores");

    }

    @Override
    protected void onItemRender(PaginatedViewSlotContext<Spawner> render, ViewItem item, Spawner value) {

        SpawnerShopUser user = render.get("user");

        item.withItem(Spawner.toItemStack(value, user)).onClick(click -> {


        });

    }
}

package com.redeseason.spawnershop.handler;

import com.google.common.collect.Lists;
import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.data.Spawner;
import lombok.Getter;

import java.util.List;

public class SpawnerHandler {

    @Getter private static final List<Spawner> spawners = Lists.newArrayList();

    public static void insert(Spawner spawner) {
        spawners.add(spawner);
        SpawnerShopPlugin.getInstance().getLogger().info("Spawner " + spawner.getName() +" carregado.");

    }

}

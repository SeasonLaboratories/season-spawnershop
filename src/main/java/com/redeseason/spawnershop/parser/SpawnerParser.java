package com.redeseason.spawnershop.parser;

import com.google.common.collect.Lists;
import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.data.Spawner;
import com.redeseason.spawnershop.handler.SpawnerHandler;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class SpawnerParser {

    public void parse() {

        for (String path : SpawnerShopPlugin.getInstance().getConfig().getConfigurationSection("spawners").getKeys(false)) {

            ConfigurationSection configurationSection = SpawnerShopPlugin.getInstance().getConfig().getConfigurationSection("spawners." + path);

            Spawner spawner = Spawner.builder()
                    .name(configurationSection.getString("name"))
                    .value(configurationSection.getDouble("price"))
                    .build();

            SpawnerHandler.insert(spawner);


        }

    }
}

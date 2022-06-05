package com.redeseason.spawnershop;

import com.redeseason.spawnershop.sql.SpawnerShopRepository;
import com.redeseason.spawnershop.sql.provider.ConnectionProvider;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import store.seasonlabs.api.libraries.sqlprovider.connector.SQLConnector;

public final class SpawnerShopPlugin extends JavaPlugin {

    @Getter private static SpawnerShopPlugin instance;
    @Getter private SQLConnector repositoryConnector;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        instance = this;

        repositoryConnector = new ConnectionProvider(this).connector();

    }
}

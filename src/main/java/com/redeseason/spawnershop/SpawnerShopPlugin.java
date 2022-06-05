package com.redeseason.spawnershop;

import com.redeseason.spawnershop.command.LimitCommand;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import com.redeseason.spawnershop.handler.listener.TrafficListener;
import com.redeseason.spawnershop.hook.EconomyHookProvider;
import com.redeseason.spawnershop.sql.SpawnerShopRepository;
import com.redeseason.spawnershop.sql.provider.ConnectionProvider;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import store.seasonlabs.api.libraries.command.CommandLibrary;
import store.seasonlabs.api.libraries.command.bukkit.BukkitFrame;
import store.seasonlabs.api.libraries.sqlprovider.connector.SQLConnector;

public final class SpawnerShopPlugin extends JavaPlugin {

    @Getter
    private static SpawnerShopPlugin instance;
    @Getter
    private SQLConnector repositoryConnector;
    @Getter
    private SpawnerShopRepository repository;
    private UserCacheHandler cacheHandler;
    private CommandLibrary commandLibrary;
    private EconomyHookProvider economyHookProvider;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        instance = this;

        repositoryConnector = new ConnectionProvider(this).connector();
        commandLibrary = new CommandLibrary(new BukkitFrame(this));
        repository = new SpawnerShopRepository();
        cacheHandler = new UserCacheHandler();

    }

    @Override
    public void onEnable() {

        repository.createTable();
        cacheHandler.loadAll();
        economyHookProvider.hook(this);
        commandLibrary.provide(this, new LimitCommand());

        Bukkit.getPluginManager().registerEvents(new TrafficListener(), this);

    }

    @Override
    public void onDisable() {
        cacheHandler.getCacheMap().values().forEach(repository::insert);

    }
}

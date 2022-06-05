package com.redeseason.spawnershop;

import com.redeseason.spawnershop.api.LimitAPI;
import com.redeseason.spawnershop.command.LimitCommand;
import com.redeseason.spawnershop.command.SpawnerCommand;
import com.redeseason.spawnershop.expansion.SeasonPlaceholder;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import com.redeseason.spawnershop.handler.listener.TrafficListener;
import com.redeseason.spawnershop.hook.EconomyHookProvider;
import com.redeseason.spawnershop.parser.SpawnerParser;
import com.redeseason.spawnershop.ranking.RankingRefreshThread;
import com.redeseason.spawnershop.ranking.view.LimitRankingView;
import com.redeseason.spawnershop.sql.SpawnerShopRepository;
import com.redeseason.spawnershop.sql.provider.ConnectionProvider;
import com.redeseason.spawnershop.view.SpawnerShopView;
import lombok.Getter;
import me.lucko.helper.Schedulers;
import me.saiintbrisson.minecraft.ViewFrame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import store.seasonlabs.api.libraries.command.CommandLibrary;
import store.seasonlabs.api.libraries.command.bukkit.BukkitFrame;
import store.seasonlabs.api.libraries.sqlprovider.connector.SQLConnector;

import java.util.concurrent.TimeUnit;

public final class SpawnerShopPlugin extends JavaPlugin {

    @Getter private static SpawnerShopPlugin instance;
    @Getter private SQLConnector repositoryConnector;
    @Getter private ViewFrame viewFrame;
    private CommandLibrary commandLibrary;
    private EconomyHookProvider economyHookProvider;
    private LimitAPI limitAPI;
    private SpawnerParser spawnerParser;
    private UserCacheHandler cacheHandler;

    @Override
    public void onLoad() {

        saveDefaultConfig();
        instance = this;

        commandLibrary = new CommandLibrary(new BukkitFrame(this));
        repositoryConnector = new ConnectionProvider(this).connector();
        economyHookProvider = new EconomyHookProvider();
        viewFrame = new ViewFrame(this);
        spawnerParser = new SpawnerParser();
        cacheHandler = new UserCacheHandler();
        limitAPI = new LimitAPI();

    }

    @Override
    public void onEnable() {

        SpawnerShopRepository.createTable();
        cacheHandler.provide();
        spawnerParser.parse();
        economyHookProvider.hook(this);
        viewFrame.register(new SpawnerShopView(), new LimitRankingView());
        commandLibrary.provide(this, new LimitCommand(), new SpawnerCommand());
        limitAPI.loadAPI(this);

        new SeasonPlaceholder().register();
        Bukkit.getPluginManager().registerEvents(new TrafficListener(), this);

    }
}

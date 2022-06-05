package com.redeseason.spawnershop.handler.listener;

import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import com.redeseason.spawnershop.sql.SpawnerShopRepository;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class TrafficListener implements Listener {

    @EventHandler
    void insertUser(PlayerQuitEvent event) {

        SpawnerShopUser user = UserCacheHandler.getHandler().getUser(event.getPlayer().getName());
        SpawnerShopRepository repository = SpawnerShopPlugin.getInstance().getRepository();

        if (user != null) {
            repository.insert(user);

        }

    }

}

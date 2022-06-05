package com.redeseason.spawnershop.handler.listener;

import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.api.LimitAPI;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import com.redeseason.spawnershop.sql.SpawnerShopRepository;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TrafficListener implements Listener {

    @EventHandler
    void loadUser(PlayerJoinEvent event) {

        SpawnerShopUser user = SpawnerShopRepository.select(event.getPlayer().getName());

        if(user == null) {
            user = SpawnerShopUser.builder()
                    .name(event.getPlayer().getName())
                    .limit(0D)
                    .build();

            SpawnerShopRepository.insert(user);

        }

        UserCacheHandler.getHandler().insertCache(user);

    }

    @EventHandler
    void insertUser(PlayerQuitEvent event) {

        SpawnerShopUser user = UserCacheHandler.getHandler().getUser(event.getPlayer().getName());

        if (user != null) {
            SpawnerShopRepository.replace(user);

        }

    }

}

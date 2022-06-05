package com.redeseason.spawnershop.command;

import com.google.common.collect.ImmutableMap;
import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import com.redeseason.spawnershop.view.SpawnerShopView;
import org.bukkit.entity.Player;
import store.seasonlabs.api.libraries.command.common.annotation.Command;
import store.seasonlabs.api.libraries.command.common.command.Context;

public class SpawnerCommand {

    @Command(
            name = "spawner",
            aliases = "spawnershop"
    )
    public void spawnerCommand(Context<Player> playerContext) {

        SpawnerShopUser user = UserCacheHandler.getHandler().getUser(playerContext.getSender().getName());

        SpawnerShopPlugin.getInstance().getViewFrame().open(
                SpawnerShopView.class,
                playerContext.getSender(),
                ImmutableMap.of("user", user)
        );

    }
}

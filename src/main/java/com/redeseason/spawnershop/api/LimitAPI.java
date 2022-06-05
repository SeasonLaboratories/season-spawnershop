package com.redeseason.spawnershop.api;

import com.google.common.collect.Lists;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import com.redeseason.spawnershop.ranking.RankingRefreshThread;
import com.redeseason.spawnershop.util.CheckUtil;
import lombok.Getter;
import lombok.Setter;
import me.lucko.helper.Schedulers;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LimitAPI {

    @Getter private static LimitAPI instance;
    @Setter @Getter private String lastTopRanking;
    @Getter private final List<SpawnerShopUser> rankingUsers = Lists.newArrayList();
    private Comparator<SpawnerShopUser> comparator = Comparator.comparingDouble(SpawnerShopUser::getLimit);

    public void loadAPI(Plugin plugin) {

        instance = this;

        UserCacheHandler.getHandler()
                .getCacheMap()
                .values()
                .stream()
                .sorted(comparator.reversed())
                .limit(10)
                .forEach(rankingUsers::add);

        Schedulers.builder()
                .async()
                .every(plugin.getConfig().getLong("ranking.flushTime"), TimeUnit.MINUTES)
                .run(new RankingRefreshThread());

    }

    public void giveCheck(Player player, Double value) {
        player.getInventory().addItem(CheckUtil.createItem(value));

    }

    public void flushRanking() {

        rankingUsers.clear();

        UserCacheHandler.getHandler()
                .getCacheMap()
                .values()
                .stream()
                .sorted(comparator.reversed())
                .limit(10)
                .forEach(rankingUsers::add);

    }


}

package com.redeseason.spawnershop.ranking;

import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.api.LimitAPI;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import store.seasonlabs.api.libraries.sound.SoundsLibrary;

public class RankingRefreshThread implements Runnable {

    @Override
    public void run() {

        if(LimitAPI.getInstance().getRankingUsers().size() > 0) {

            LimitAPI.getInstance().flushRanking();
            SpawnerShopUser user = LimitAPI.getInstance().getRankingUsers().get(0);

            if(user.getName() == LimitAPI.getInstance().getLastTopRanking()) return;

            Bukkit.getOnlinePlayers().forEach(player -> {

                player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
                player.sendMessage(new String[] {
                        "",
                        "§b§l  RANKING DE LIMITE!",
                        "§f  " + user.getName() + "§7 tornou-se o novo TOP 1.",
                        ""
                });

            });

            LimitAPI.getInstance().setLastTopRanking(user.getName());
            if(SpawnerShopPlugin.getInstance().getConfig().getBoolean("ranking.debug"))
                Bukkit.broadcast("§8[DEBUG - SPAWNERSHOP] Ranking thread rodou.", "group.gm");
        }

    }
}

package com.redeseason.spawnershop.expansion;

import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import store.seasonlabs.api.libraries.formatter.NumberLibrary;

public class SeasonPlaceholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "rankup";
    }

    @Override
    public @NotNull String getAuthor() {
        return "RedeSeason";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {

        final SpawnerShopUser user = UserCacheHandler.getHandler().getUser(player.getName());

        if(user == null) {
            return "§c...";

        }

        if(params.equalsIgnoreCase("limite")) {
            return NumberLibrary.format(user.getLimit());

        }

        return super.onPlaceholderRequest(player,params);

    }
}

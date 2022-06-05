package com.redeseason.spawnershop.command;

import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.handler.UserCacheHandler;
import org.bukkit.entity.Player;
import store.seasonlabs.api.libraries.command.common.annotation.Command;
import store.seasonlabs.api.libraries.command.common.annotation.Optional;
import store.seasonlabs.api.libraries.command.common.command.Context;
import store.seasonlabs.api.libraries.formatter.NumberLibrary;

public class LimitCommand {

    @Command(
            name = "limit",
            aliases = {"limite"}
    )
    public void limitCommand(Context<Player> playerContext, @Optional Player targetContext) {

        SpawnerShopUser user = UserCacheHandler.getHandler().getUser(playerContext.getSender().getName());
        if (targetContext != null) user = UserCacheHandler.getHandler().getUser(targetContext.getName());

        playerContext.sendMessage("§aO jogador " + user.getName() + " tem " + NumberLibrary.format(user.getLimit()) + " de limite.");

    }

    @Command(
            name = "limit.set",
            aliases = "limite.set",
            permission = "seasonspawners.limit.add"
    )
    public void limitSetCommand(Context<Player> playerContext, Player targetContext, Double amount) {

        SpawnerShopUser user = UserCacheHandler.getHandler().getUser(targetContext.getName());

        if (user == null) {
            targetContext.sendMessage("§cO seu usuário não carregou corretamente, relogue.");
            return;

        }

        user.setLimit(amount);
        playerContext.sendMessage("§bLimite de §f" + targetContext.getName() + "§b definido para §f" + NumberLibrary.format(user.getLimit()) + "§b.");

    }

}

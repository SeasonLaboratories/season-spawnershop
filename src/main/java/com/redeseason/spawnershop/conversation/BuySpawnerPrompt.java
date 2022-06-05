package com.redeseason.spawnershop.conversation;

import com.redeseason.spawnershop.data.Spawner;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.hook.EconomyHookProvider;
import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import store.seasonlabs.api.libraries.formatter.NumberLibrary;

public class BuySpawnerPrompt extends NumericPrompt {
    @Override
    public String getPromptText(ConversationContext context) {
        SpawnerShopUser user = (SpawnerShopUser) context.getSessionData("user");
        return "§r \n  §e§lCOMPRA DE GERADORES! \n  §fDigite quantos geradores você deseja comprar, \n  §fo seu limite é §7" + NumberLibrary.format(user.getLimit()) + "§f.\n §r";

    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext context, Number input) {

        SpawnerShopUser user = (SpawnerShopUser) context.getSessionData("user");
        Player player = (Player) context.getForWhom();

        if (!EconomyHookProvider.getEconomy().has(player.getName(), input.doubleValue()) ||
                user.getLimit() < input.doubleValue()) {

            player.sendMessage(new String[]{
                    "",
                    "§c§l  OPS!",
                    "§c  Você não tem coins suficientes",
                    "§c  para comprar esta quantia de geradores.",
                    ""
            });

            return END_OF_CONVERSATION;

        }

        Double moneyToRemove = input.doubleValue() * user.getBuyingSpawner().getValue();

        EconomyHookProvider.getEconomy().withdrawPlayer(player.getName(), moneyToRemove);
        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "spawneradmin give " + player.getName() + " " + user.getBuyingSpawner().getName() + " 1"
        );

        player.sendMessage(new String[] {
                "",
                "§a§l GG!§f Você comprou §7" + input.doubleValue() + "Gerador de " + user.getBuyingSpawner().getName() + "",
                "§f  por §7" + NumberLibrary.format(moneyToRemove) +"§f coins.",
                ""
        });

        user.setBuyingSpawner(null);

        return END_OF_CONVERSATION;
    }
}

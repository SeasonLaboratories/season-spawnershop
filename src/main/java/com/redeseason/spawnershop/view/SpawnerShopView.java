package com.redeseason.spawnershop.view;

import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.conversation.BuySpawnerPrompt;
import com.redeseason.spawnershop.data.Spawner;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.handler.SpawnerHandler;
import me.saiintbrisson.minecraft.PaginatedView;
import me.saiintbrisson.minecraft.PaginatedViewSlotContext;
import me.saiintbrisson.minecraft.ViewContext;
import me.saiintbrisson.minecraft.ViewItem;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnerShopView extends PaginatedView<Spawner> {

    public SpawnerShopView() {

        super(6, "Loja de Geradores");

        setLayout(
                "XXXXXXXXX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XOOOOOOOX",
                "XXXXXXXXX"
        );

    }

    @Override
    protected void onRender(@NotNull ViewContext context) {
        setSource(SpawnerHandler.getSpawners());

    }

    @Override
    protected void onItemRender(PaginatedViewSlotContext<Spawner> render, ViewItem item, Spawner value) {

        SpawnerShopUser user = render.get("user");

        item.withItem(Spawner.toItemStack(value, user)).onClick(click -> {

            Player player = click.getPlayer();

            user.setBuyingSpawner(value);

            Conversation conversation = new ConversationFactory(SpawnerShopPlugin.getInstance())
                    .withFirstPrompt(new BuySpawnerPrompt())
                    .withTimeout(30)
                    .withLocalEcho(false)
                    .buildConversation(player);

            conversation.getContext().setSessionData("user", user);
            conversation.begin();

            close();

        });

    }
}

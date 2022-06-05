package com.redeseason.spawnershop.hook;

import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyHookProvider {

    @Getter
    private static Economy economy;

    public void hook(Plugin plugin) {

        final RegisteredServiceProvider<Economy> registration = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (registration == null) return;

        economy = registration.getProvider();

    }

}

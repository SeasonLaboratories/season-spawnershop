package com.redeseason.spawnershop.sql;

import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.sql.adapter.SpawnerShopAdapter;
import store.seasonlabs.api.libraries.sqlprovider.executor.SQLExecutor;

import java.util.Set;

public class SpawnerShopRepository {

    private static final String spawnerShopTable = "spawnershop";

    public static void createTable() {

        sqlExecutor().updateQuery(
                "CREATE TABLE IF NOT EXISTS " + spawnerShopTable + "(" +
                        "playerName VARCHAR(16) NOT NULL PRIMARY KEY," +
                        "limit_value DOUBLE NOT NULL" +
                        ");"
        );

    }

    public static void insert(SpawnerShopUser user) {
        sqlExecutor().updateQuery(
                "INSERT INTO " + spawnerShopTable + " VALUES(?,?)",
                queryConsumer -> {
                    queryConsumer.set(1, user.getName());
                    queryConsumer.set(2, user.getLimit());

                }
        );

    }

    public static void replace(SpawnerShopUser user) {
        sqlExecutor().updateQuery(
                "REPLACE INTO " + spawnerShopTable + " VALUES(?,?)",
                queryConsumer -> {
                    queryConsumer.set(1, user.getName());
                    queryConsumer.set(2, user.getLimit());

                }
        );

    }


    public static SpawnerShopUser select(String name) {
        return sqlExecutor().resultOneQuery(
                "SELECT * FROM " + spawnerShopTable + " WHERE playerName = ?",
                queryConsumer -> {
                    queryConsumer.set(1, name);
                },
                SpawnerShopAdapter.class
        );

    }

    private static SQLExecutor sqlExecutor() {
        return new SQLExecutor(SpawnerShopPlugin.getInstance().getRepositoryConnector());
    }

}

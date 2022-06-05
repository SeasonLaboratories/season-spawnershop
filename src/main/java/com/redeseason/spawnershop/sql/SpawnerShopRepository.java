package com.redeseason.spawnershop.sql;

import com.redeseason.spawnershop.SpawnerShopPlugin;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.sql.adapter.SpawnerShopAdapter;
import store.seasonlabs.api.libraries.sqlprovider.executor.SQLExecutor;

import java.util.Set;

public class SpawnerShopRepository {

    private final String spawnerShopTable = "spawnershop";

    public void createTable() {

        sqlExecutor().updateQuery(
                "CREATE TABLE IF NOT EXISTS " + spawnerShopTable + " (" +
                        "user VARCHAR(16) NOT NULL PRIMARY KEY," +
                        "limit DOUBLE NOT NULL" +
                        ");"
        );

    }

    public void insert(SpawnerShopUser user) {
        sqlExecutor().updateQuery(
                "INSERT INTO " + spawnerShopTable + " VALUES(?,?)",
                queryConsumer -> {
                    queryConsumer.set(1, user.getName());
                    queryConsumer.set(2, user.getLimit());

                }
        );

    }

    public Set<SpawnerShopUser> selectAll() {
        return sqlExecutor().resultManyQuery(
                "SELECT * FROM " + spawnerShopTable,
                queryConsumer -> {
                },
                SpawnerShopAdapter.class
        );

    }

    private SQLExecutor sqlExecutor() {
        return new SQLExecutor(SpawnerShopPlugin.getInstance().getRepositoryConnector());
    }

}

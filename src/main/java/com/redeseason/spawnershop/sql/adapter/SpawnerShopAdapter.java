package com.redeseason.spawnershop.sql.adapter;

import com.redeseason.spawnershop.data.SpawnerShopUser;
import store.seasonlabs.api.libraries.sqlprovider.executor.adapter.SQLResultAdapter;
import store.seasonlabs.api.libraries.sqlprovider.executor.result.SimpleResultSet;

public class SpawnerShopAdapter implements SQLResultAdapter<SpawnerShopUser> {

    @Override
    public SpawnerShopUser adaptResult(SimpleResultSet resultSet) {
        return SpawnerShopUser.builder()
                .name(resultSet.get("playerName"))
                .limit(resultSet.get("limit_value"))
                .build();
    }
}

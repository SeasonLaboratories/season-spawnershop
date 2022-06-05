package com.redeseason.spawnershop.handler;

import com.google.common.collect.Maps;
import com.redeseason.spawnershop.data.SpawnerShopUser;
import com.redeseason.spawnershop.sql.SpawnerShopRepository;
import lombok.Getter;

import java.util.Map;

public class UserCacheHandler {

    @Getter
    private static UserCacheHandler handler;
    @Getter
    private final Map<String, SpawnerShopUser> cacheMap = Maps.newLinkedHashMap();

    public SpawnerShopUser getUser(String name) {
        return cacheMap.get(name);

    }

    public void loadAll() {

        SpawnerShopRepository repository = new SpawnerShopRepository();

        handler = this;
        repository.selectAll().forEach(this::insertCache);

    }

    private void insertCache(SpawnerShopUser user) {
        cacheMap.put(user.getName(), user);

    }


}

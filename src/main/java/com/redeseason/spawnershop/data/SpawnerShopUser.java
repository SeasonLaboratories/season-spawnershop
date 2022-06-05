package com.redeseason.spawnershop.data;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SpawnerShopUser {

    private String name;
    private Double limit;

}

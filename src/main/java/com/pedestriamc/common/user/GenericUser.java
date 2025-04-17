package com.pedestriamc.common.user;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class GenericUser extends AbstractUser implements Buildable<GenericUser> {

    public GenericUser(Player player) {
        super(player);

    }

    @Override
    public UUID getUuid() {
        return null;
    }

    @Override
    public Map<String, String> getData() {
        return Map.of();
    }

    @Override
    public GenericUser build(Map<String, String> map) {
        return null;
    }
}

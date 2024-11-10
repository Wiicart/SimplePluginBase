package com.pedestriamc.common.user;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface User {

    Player getPlayer();
    UUID getUuid();
    Map<String, String> getData();

}

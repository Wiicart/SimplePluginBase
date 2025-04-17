package com.pedestriamc.common.user;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public interface User {

    /**
     * Provides the Player this User is associated with.
     * @return A Player.
     */
    Player getPlayer();

    /**
     * The UUID of this User. Should match the UUID of the Player.
     * @return The User's UUID.
     */
    UUID getUuid();

    /**
     * Provides the Data this User holds.
     * @return A populated Map.
     */
    Map<String, String> getData();

}

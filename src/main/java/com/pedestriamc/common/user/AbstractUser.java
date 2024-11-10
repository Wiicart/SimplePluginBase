package com.pedestriamc.common.user;

import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class AbstractUser implements User
{

    private final Player player;
    private final UUID uuid;

    protected AbstractUser(Player player)
    {
        this.player = player;
        uuid = player.getUniqueId();
    }

    public Player getPlayer(){
        return player;
    }

    public UUID getUniqueID(){
        return uuid;
    }

}

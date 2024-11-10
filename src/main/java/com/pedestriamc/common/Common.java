package com.pedestriamc.common;

import com.pedestriamc.common.user.UserUtil;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Common extends JavaPlugin {

    private void log(String message){
        getLogger().info(message);
    }

    abstract UserUtil getUserUtil();


}

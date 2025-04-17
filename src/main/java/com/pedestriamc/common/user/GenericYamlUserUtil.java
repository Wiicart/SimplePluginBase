package com.pedestriamc.common.user;

import org.bukkit.configuration.file.FileConfiguration;

public class GenericYamlUserUtil<T extends User> extends AbstractUserUtil<T> {

    public GenericYamlUserUtil(FileConfiguration configuration){
        super();
    }

    @Override
    public void saveUser(T user) {

    }

    @Override
    public void loadUser(T uuid) {

    }
}

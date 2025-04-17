package com.pedestriamc.common.user;

public abstract class AbstractUserUtil<T extends User > implements UserUtil<T> {

    private final UserMap<T> userMap;

    protected AbstractUserUtil() {
        userMap = new UserMap<>();
    }

    public UserMap<T> getUserMap() {
        return userMap;
    }

}

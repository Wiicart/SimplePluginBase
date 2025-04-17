package com.pedestriamc.common.user;

import java.util.Map;

/**
 * An interface that provides a method build, allowing generic building of Users.
 * @param <T> Should match the class that implements this method.
 */
public interface Buildable<T extends User> {

    /**
     * Builds a User from the data in the given Map.
     * Should be a STATIC method.
     * @param map The Map containing data.
     * @return A new User.
     */
    T build(Map<String, String> map);

}

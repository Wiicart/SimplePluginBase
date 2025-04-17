package com.pedestriamc.common.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public interface UserUtil<T extends User> {

    /**
     * Saves a User
     * @param user The user to be saved
     */
    void saveUser(T user);

    /**
     * Loads a User
     * @param uuid The UUID of the User to be loaded.
     */
    void loadUser(T uuid);

    /**
     * Provides the UserMap containing loaded players.
     * @return The UserMap for this instance.
     */
    UserMap<T> getUserMap();

    /**
     * A Map containing all loaded Users.
     * Users should be removed at log-out.
     * @param <T> The User implementation
     */
    public class UserMap<T extends User> {

        private HashMap<UUID, T> map;

        protected UserMap(){

        }

        public T getUser(Player player){
            return getUser(player.getUniqueId());
        }

        public T getUser(UUID uuid){
            return map.get(uuid);
        }

        public void addUser(T t) {
            map.put(t.getUuid(), t);
        }

        public void removeUser(T t){
            removeUser(t.getUuid());
        }

        public void removeUser(UUID uuid){
            map.remove(uuid);
        }

    }

}

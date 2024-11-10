package com.pedestriamc.common.user;

import java.util.UUID;

public interface UserUtil {

    void saveUser(User user);

    void loadUser(UUID uuid);

}

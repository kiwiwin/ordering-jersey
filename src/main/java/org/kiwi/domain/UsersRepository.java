package org.kiwi.domain;

import org.apache.ibatis.annotations.Param;

public interface UsersRepository {
    User getUserById(@Param("userId") int userId);

    void createUser(@Param("user") User user);
}

package org.kiwi.domain;

public class UserIWithId {
    public static User userWithId(int id, User user) {
        user.id = id;
        return user;
    }
}

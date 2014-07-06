package org.kiwi.domain;

public class User {
    int id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

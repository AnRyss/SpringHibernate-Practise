package com.fishing.FishingGame.DomainEntities;

import jakarta.persistence.Column;

public class User {
    private  String username;
    private  String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

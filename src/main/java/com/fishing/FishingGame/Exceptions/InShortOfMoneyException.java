package com.fishing.FishingGame.Exceptions;

import java.util.UUID;

public class InShortOfMoneyException extends RuntimeException {
    private UUID uuid;
    private double money;

    public InShortOfMoneyException(UUID uuid, double money) {
        this.uuid = uuid;
        this.money = money;

    }

    public InShortOfMoneyException(String message) {
        super(message);
    }
}

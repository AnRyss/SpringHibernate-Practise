package com.fishing.FishingGame.Exceptions;

import java.util.UUID;

public class InShortOfMoneyException extends RuntimeException {
    private final UUID uuid;
    private final double money;
    public InShortOfMoneyException(UUID uuid, double money) {
        this.uuid = uuid;
        this.money = money;
    }
}

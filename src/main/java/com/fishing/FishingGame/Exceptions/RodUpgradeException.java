package com.fishing.FishingGame.Exceptions;

import com.fishing.FishingGame.Domain.Player;

import java.util.UUID;

public class RodUpgradeException extends RuntimeException {
    private UUID playerUuid;

    public RodUpgradeException(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    public RodUpgradeException(String message) {
        super(message);
    }
}

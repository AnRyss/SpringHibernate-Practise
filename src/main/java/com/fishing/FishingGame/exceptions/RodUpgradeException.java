package com.fishing.FishingGame.exceptions;

import java.util.UUID;

public class RodUpgradeException extends RuntimeException {
    public UUID getPlayerUuid() {
        return playerUuid;
    }

    private UUID playerUuid;

    public RodUpgradeException(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    public RodUpgradeException(String message) {
        super(message);
    }
}

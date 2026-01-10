package com.fishing.FishingGame.Dto;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.Player;

public record FishingContext(
        AbstractLocation location,
        Player player,
        Double luckModifier) {
    public static FishingContext create(Player player) {
        return new FishingContext(
                player.getCurrentLocation(),
               player,
                player.getLuck()

        );

    }
}

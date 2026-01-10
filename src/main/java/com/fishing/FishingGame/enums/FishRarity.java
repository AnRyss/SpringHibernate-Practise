package com.fishing.FishingGame.enums;

import java.util.Optional;

public enum FishRarity {
    COMMON(70,"UNCOMMON"),
    UNCOMMON(20,"EPIC"),
    EPIC(5,"LEGENDARY"),
    LEGENDARY(1,null);
private final long chance;
private final String nextRarity;
    FishRarity(long chance,String nextRarity) {

        this.chance = chance;
        this.nextRarity = nextRarity;
    }
    public long getChance(){
        return chance;
    }
    public Optional<FishRarity> getNext() {
        if (nextRarity == null) {
            return Optional.empty();
        }
        return Optional.of(FishRarity.valueOf(nextRarity));
    }
}

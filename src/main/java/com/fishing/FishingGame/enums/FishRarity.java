package com.fishing.FishingGame.enums;

public enum FishRarity {
    COMMON(70),
    UNCOMMON(20),
    EPIC(5),
    LEGENDARY(5);
private final long chance;
    FishRarity(long chance) {
        this.chance = chance;
    }
    public long getChance(){
        return chance;
    }
}

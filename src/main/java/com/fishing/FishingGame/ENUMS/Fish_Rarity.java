package com.fishing.FishingGame.ENUMS;

public enum Fish_Rarity {
    COMMON(70),
    UNCOMMON(20),
    EPIC(5),
    LEGENDARY(5);
private final long chance;
    Fish_Rarity(long chance) {
        this.chance = chance;
    }
    public long getChance(){
        return chance;
    }
}

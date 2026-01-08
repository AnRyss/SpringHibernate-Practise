package com.fishing.FishingGame.enums;

import com.fishing.FishingGame.Domain.Items.Fish;

public enum FishType {
    CARP("Карп", 2.5, FishRarity.COMMON, 10L),
    PIKE("Щука", 4.0, FishRarity.UNCOMMON, 25L),
    SALMON("Лосось", 5.0, FishRarity.EPIC, 50L),
    STURGEON("Осётр", 15.0, FishRarity.EPIC, 100L),
    GOLDEN_FISH("Золотая рыбка", 0.1, FishRarity.LEGENDARY, 100000L),
     VOID_FISH("Вселенно-пустое существо",100000.0, FishRarity.LEGENDARY, 1000000000L);
    private final String name;
    private final double baseWeight;
    private final long basePrice;
private final FishRarity rarity;
    FishType(String name, double baseWeight, FishRarity rarity, Long basePrice) {
        this.name = name;
        this.baseWeight = baseWeight;

        this.basePrice = basePrice;
        this.rarity = rarity;
    }
    public Fish createFish() {
        return new Fish(this.name, this.baseWeight, this.rarity, this.basePrice);
    }
    public FishRarity getRarity(){
        return rarity;
    }
}



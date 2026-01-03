package com.fishing.FishingGame.ENUMS;

import com.fishing.FishingGame.Game.Fish;

public enum FishType {
    // Конструктор: название, вес, редкость, цена
    CARP("Карп", 2.5, FISHRARITY.COMMON, 10L),
    PIKE("Щука", 4.0, FISHRARITY.UNCOMMON, 25L),
    SALMON("Лосось", 5.0, FISHRARITY.EPIC, 50L),
    STURGEON("Осётр", 15.0, FISHRARITY.EPIC, 100L),
    GOLDEN_FISH("Золотая рыбка", 0.1, FISHRARITY.LEGENDARY, 100000L),
     VOID_FISH("Вселенно-пустое существо",100000.0,FISHRARITY.LEGENDARY, 1000000000L);
    private final String name;
    private final double baseWeight;
    private final long basePrice;
private final FISHRARITY rarity;
    FishType(String name, double baseWeight, FISHRARITY rarity, Long basePrice) {
        this.name = name;
        this.baseWeight = baseWeight;

        this.basePrice = basePrice;
        this.rarity = rarity;
    }
    public Fish createFish() {
        return new Fish(this.name, this.baseWeight, this.rarity, this.basePrice);
    }
}



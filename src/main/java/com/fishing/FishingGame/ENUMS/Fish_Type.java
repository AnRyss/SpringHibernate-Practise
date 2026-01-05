package com.fishing.FishingGame.ENUMS;

import com.fishing.FishingGame.DomainEntities.Fish;

public enum Fish_Type {
    // Конструктор: название, вес, редкость, цена
    CARP("Карп", 2.5, Fish_Rarity.COMMON, 10L),
    PIKE("Щука", 4.0, Fish_Rarity.UNCOMMON, 25L),
    SALMON("Лосось", 5.0, Fish_Rarity.EPIC, 50L),
    STURGEON("Осётр", 15.0, Fish_Rarity.EPIC, 100L),
    GOLDEN_FISH("Золотая рыбка", 0.1, Fish_Rarity.LEGENDARY, 100000L),
     VOID_FISH("Вселенно-пустое существо",100000.0, Fish_Rarity.LEGENDARY, 1000000000L);
    private final String name;
    private final double baseWeight;
    private final long basePrice;
private final Fish_Rarity rarity;
    Fish_Type(String name, double baseWeight, Fish_Rarity rarity, Long basePrice) {
        this.name = name;
        this.baseWeight = baseWeight;

        this.basePrice = basePrice;
        this.rarity = rarity;
    }
    public Fish createFish() {
        return new Fish(this.name, this.baseWeight, this.rarity, this.basePrice);
    }
    public Fish_Rarity getRarity(){
        return rarity;
    }
}



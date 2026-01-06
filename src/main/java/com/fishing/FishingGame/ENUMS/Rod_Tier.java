package com.fishing.FishingGame.ENUMS;

import com.fishing.FishingGame.DomainEntities.Rod;

public enum Rod_Tier {
    COMMON(500,"Обычная"),

    UNCOMMON(1500,"Необычная"),

    EPIC(5000,"Эпическая"),

    LEGENDARY(1500000,"Легендарная"),

    ADMIN(0,"Админская");
    private final int defaultUpgradePrice;
    private final String displayName;

    Rod_Tier(int defaultUpgradePrice, String displayName) {
        this.defaultUpgradePrice = defaultUpgradePrice;
        this.displayName = displayName;
    }
    public int getPrice(){
        return defaultUpgradePrice;
    }
}

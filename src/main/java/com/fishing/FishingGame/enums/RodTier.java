package com.fishing.FishingGame.enums;

import java.util.Optional;

public enum RodTier {
    COMMON(500,"Обычная","UNCOMMON"),

    UNCOMMON(1500,"Необычная","EPIC"),

    EPIC(5000,"Эпическая","LEGENDARY"),

    LEGENDARY(1500000,"Легендарная",null),

    ADMIN(0,"Админская",null);
    private final int defaultUpgradePrice;
    private final String displayName;
    private final String nextTier;
    RodTier(int defaultUpgradePrice, String displayName, String nextTier) {
        this.defaultUpgradePrice = defaultUpgradePrice;
        this.displayName = displayName;
        this.nextTier = nextTier;
    }
    public int getPrice(){
        return defaultUpgradePrice;
    }
    public Optional<RodTier> getNext() {
        if (nextTier == null) {
            return Optional.empty();
        }
        return Optional.of(RodTier.valueOf(nextTier));
    }
}

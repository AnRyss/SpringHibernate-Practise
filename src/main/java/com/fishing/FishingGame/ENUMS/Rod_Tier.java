package com.fishing.FishingGame.ENUMS;

public enum Rod_Tier {

    COMMON(500, "Обычная"),
    UNCOMMON(1500, "Необычная"),
    EPIC(5000, "Эпическая"),
    LEGENDARY(1500000, "Легендарная"),
    ADMIN(0, "Админская");

    Rod_Tier(double defaultUpgradePrice, String displayName) {
    }
}

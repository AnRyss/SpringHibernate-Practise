package com.fishing.FishingGame.ENUMS;

public enum RODTIER {

    COMMON(500, "Обычная"),
    UNCOMMON(1500, "Необычная"),
    EPIC(5000, "Эпическая"),
    LEGENDARY(1500000, "Легендарная"),
    ADMIN(0, "Админская");

    RODTIER(double defaultUpgradePrice, String displayName) {
    }
}

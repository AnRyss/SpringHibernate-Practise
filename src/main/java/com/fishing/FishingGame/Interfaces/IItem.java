package com.fishing.FishingGame.Interfaces;

import com.fishing.FishingGame.enums.ItemType;

public interface IItem {
    String getName();
    ItemType getType(); // ROD, BAIT, CONSUMABLE, etc.
}

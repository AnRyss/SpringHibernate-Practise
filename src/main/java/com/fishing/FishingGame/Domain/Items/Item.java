package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.ItemType;

public class Item implements IItem {
    private final ItemType type;
    private final String name;

    public Item(ItemType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public ItemType getType() {
        return null;
    }
}

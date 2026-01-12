package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.ItemType;

public class NotPhysicalItem implements IItem {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public ItemType getType() {
        return null;
    }
}

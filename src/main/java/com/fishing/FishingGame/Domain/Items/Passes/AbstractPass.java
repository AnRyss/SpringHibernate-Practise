package com.fishing.FishingGame.Domain.Items.Passes;

import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.ItemType;

public abstract class AbstractPass implements IItem {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ItemType getType() {
        return ItemType.Pass;
    }
}

package com.fishing.FishingGame.Domain.Items.Passes;

import com.fishing.FishingGame.Domain.Items.IItem;
import com.fishing.FishingGame.enums.ItemType;

public abstract class AbstractPass extends IItem {

    public AbstractPass() {
        super(ItemType.Pass, false);
    }
    @Override
    public ItemType getType() {
        return ItemType.Pass;
    }
}

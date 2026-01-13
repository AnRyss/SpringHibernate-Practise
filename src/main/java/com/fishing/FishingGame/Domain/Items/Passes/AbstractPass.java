package com.fishing.FishingGame.Domain.Items.Passes;

import com.fishing.FishingGame.Domain.Items.NotPhysicalItem;
import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.ItemType;

public abstract class AbstractPass extends NotPhysicalItem {
    private String name;
    public void setName(String name) {
        this.name = name;
    }




    @Override
    public String getName() {return name;
    }

    @Override
    public ItemType getType() {
        return ItemType.Pass;
    }
}

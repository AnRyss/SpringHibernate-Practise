package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;


public abstract class NotPhysicalItem extends IItem {
    public NotPhysicalItem() {
        super(false);
    }

    public NotPhysicalItem(Long id, String name) {
        super(false,id);
        super.setName(name);
    }

}

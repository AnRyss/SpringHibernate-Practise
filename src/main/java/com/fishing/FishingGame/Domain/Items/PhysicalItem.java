package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;


public abstract class PhysicalItem extends IItem {

    public PhysicalItem(String name, Long id) {
        super(true, id);
        super.setName(name);
    }

    public PhysicalItem() {
        super(true);
    }
}

package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;


public abstract class NotPhysicalItem implements IItem {
   private Long id;
   private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
    this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }


}

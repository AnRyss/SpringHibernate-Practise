package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.enums.ItemType;

public class ItemAttribute extends NotPhysicalItem{
    private String name;  // "strength", "hook_chance", "durability"
    private Double value;
    public void setValue(Double value){
        this.value = value;
    }
    public Double getValue() {
    return this.value;
    }

    public String getName() {
        return this.name;
    }
    @Override
    public void setName(String name) {

    }

    @Override
    public ItemType getType() {
        return ItemType.Attribute;
    }
}

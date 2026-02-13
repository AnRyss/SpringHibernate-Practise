package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.enums.ItemType;

public class ItemAttribute {
    private String name;  // "strength", "hook_chance", "durability"
    private Double value;
    private String stringValue;
    public ItemAttribute setValue(Double value){
        this.value = value;
        return this;
    }
    public Double getValue() {
    return this.value;
    }
    public String getStringValue(){
        return stringValue;
    }
    public String getName() {
        return this.name;
    }

    public ItemAttribute setName(String name) {
        this.name = name;
        return this;
    }
    public ItemAttribute setStringValue(String stringValue){
        this.stringValue = stringValue;
        return this;
    }

    public ItemType getType() {
        return ItemType.Attribute;
    }
}

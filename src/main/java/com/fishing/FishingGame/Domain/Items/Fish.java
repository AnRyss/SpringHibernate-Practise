package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.enums.FishRarity;
import com.fishing.FishingGame.enums.ItemType;


public class Fish extends PhysicalItem {
    private ItemAttribute size;
    private FishRarity rarity;
    private ItemAttribute cost;


    public Fish() {
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public Fish(String name, double size, FishRarity rarity, double cost) {
        super.setName(name);
        this.size.setName("size");
        this.size.setValue(size);
        this.cost.setName("cost");
        this.rarity = rarity;
        this.cost.setValue(cost);
    }

    @Override
    public ItemType getType() {
        return ItemType.Fish;
    }

    public double getSize() {
        return size.getValue();
    }

    public FishRarity getRarity() {
        return rarity;
    }

    public double getCost() {
        return cost.getValue();
    }

}

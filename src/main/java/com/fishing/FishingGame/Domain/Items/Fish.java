package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.enums.FishRarity;
import com.fishing.FishingGame.enums.ItemType;


public class Fish extends PhysicalItem {
    private double size;
    private FishRarity rarity;
    private double cost;


    public Fish() {
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public Fish(String name, double size, FishRarity rarity, double cost) {
        super.setName(name);
        this.size = size;
        this.rarity = rarity;
        this.cost = cost;
    }

    @Override
    public ItemType getType() {
        return ItemType.Fish;
    }

    public double getSize() {
        return size;
    }

    public FishRarity getRarity() {
        return rarity;
    }

    public double getCost() {
        return cost;
    }

}

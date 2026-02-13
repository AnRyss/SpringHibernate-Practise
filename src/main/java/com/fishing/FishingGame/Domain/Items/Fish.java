package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.enums.FishRarity;
import com.fishing.FishingGame.enums.ItemType;


public class Fish extends IItem {
    private ItemAttribute size = new ItemAttribute();
    private ItemAttribute rarity = new ItemAttribute();
    private ItemAttribute cost = new ItemAttribute();

    @Override
    public String getName() {
        return super.getName();
    }

    public Fish(String name, double size, FishRarity rarity, double cost) {
        super(ItemType.Fish,true);
        super.setName(name);
        this.size.setName("size");
        this.size.setValue(size);
        this.cost.setName("cost");
        this.rarity.setName("rarity");
        this.rarity.setStringValue(rarity.name());
        this.cost.setValue(cost);
        super.addAttribute(this.size);
        super.addAttribute(this.rarity);
        super.addAttribute(this.cost);
    }

    @Override
    public ItemType getType() {
        return ItemType.Fish;
    }

    public double getSize() {
        return size.getValue();
    }

    public FishRarity getRarity() {
        return FishRarity.valueOf(super.getAttributeByName("rarity").getStringValue());
    }

    public double getCost() {
        return cost.getValue();
    }

}

package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.enums.ItemType;
import com.fishing.FishingGame.enums.RodTier;
import java.util.Objects;


public class Rod extends IItem {
    private ItemAttribute rodTier;
    private ItemAttribute durability;
    public Rod(RodTier rodTier) {
        super(ItemType.Rod,true);
        this.rodTier.setName("rodTier");
        this.rodTier.setStringValue(rodTier.name());
        this.durability.setValue(100.0);
        this.durability.setName("durability");
        super.setName(rodTier.name() + " удочка");
        super.addAttribute(this.rodTier);
        super.addAttribute(this.durability);

    }
    public RodTier getRodtier() {
        return RodTier.valueOf(super.getAttributeByName("rodTier").getStringValue());
    }

    public void setRodtier(RodTier rodTier) {
        super.getAttributeByName("rodTier").setStringValue(rodTier.name());
        this.rodTier =  super.getAttributeByName("rodTier");

    }

    public double getDurability() {
        return durability.getValue();
    }

    public void setDurability(double durability) {
        this.durability.setValue(durability);
    }

    public boolean isFishable() {
        return this.getDurability() > 0;
    }


    @Override
    public String getName() {
        return rodTier.getName()+ " удочка";
    }

    @Override
    public ItemType getType() {
        return ItemType.Rod;
    }



    @Override
    public int hashCode() {
        return Objects.hash(rodTier, durability);
    }
}

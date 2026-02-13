package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.enums.ItemType;
import com.fishing.FishingGame.enums.RodTier;
import java.util.Objects;


public class Rod extends PhysicalItem {
    private RodTier rodTier;
    private ItemAttribute durability;
    public Rod(RodTier rodTier) {
        this.rodTier = rodTier;
        this.durability.setValue(100.0);
        this.durability.setName("durability");
        super.setName(rodTier.name() + " удочка");
    }

    private Rod() {
    }


    public RodTier getRodtier() {
        return rodTier;
    }

    public void setRodtier(RodTier rodTier) {
        this.rodTier = rodTier;
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
        return rodTier.name() + " удочка";
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

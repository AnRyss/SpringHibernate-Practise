package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.ItemType;
import com.fishing.FishingGame.enums.RodTier;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Rod implements IItem {
    private RodTier rodTier;
    private double durability;

    public Rod(RodTier rodTier) {
        this.rodTier = rodTier;
        this.durability = 100;
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
        return durability;
    }

    public void setDurability(double durability) {
        this.durability = durability;
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rod rod = (Rod) o;
        return Double.compare(durability, rod.durability) == 0 && rodTier == rod.rodTier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rodTier, durability);
    }
}

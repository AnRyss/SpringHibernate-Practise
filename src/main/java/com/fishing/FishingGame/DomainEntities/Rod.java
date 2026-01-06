package com.fishing.FishingGame.DomainEntities;

import com.fishing.FishingGame.enums.RodTier;
import jakarta.persistence.Embeddable;

@Embeddable
public class Rod {
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


}

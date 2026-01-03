package com.fishing.FishingGame.DomainEntities;

import com.fishing.FishingGame.ENUMS.Rod_Tier;
import jakarta.persistence.Embeddable;

@Embeddable
public class Rod {
    private Rod_Tier rodTier;
    private double durability;

    public Rod(Rod_Tier rodTier) {
        this.rodTier = rodTier;
        this.durability = 100;
    }
private Rod(){}
    public Rod_Tier getRodtier() {
        return rodTier;
    }

    public void setRodtier(Rod_Tier rodTier) {
        this.rodTier = rodTier;
    }

    public double getDurability() {
        return durability;
    }

    public void setDurability(double durability) {
        this.durability = durability;
    }
    public boolean isFishable(){
        return this.getDurability() > 0;
    }


}

package com.fishing.FishingGame.Game;

import com.fishing.FishingGame.ENUMS.RODTIER;
import jakarta.persistence.Embeddable;

@Embeddable
public class Rod {
    private RODTIER rodtier;
    private double durability;

    public Rod(RODTIER rodtier) {
        this.rodtier = rodtier;
        this.durability = 100;
    }
private Rod(){}
    public RODTIER getRodtier() {
        return rodtier;
    }

    public void setRodtier(RODTIER rodtier) {
        this.rodtier = rodtier;
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

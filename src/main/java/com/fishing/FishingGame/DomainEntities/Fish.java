package com.fishing.FishingGame.DomainEntities;

import com.fishing.FishingGame.enums.FishRarity;
import jakarta.persistence.Embeddable;

@Embeddable
public record Fish(
        String name,
        double size,
        FishRarity rarity,
        double cost)
{
    public Fish(String name, double size, FishRarity rarity, double cost){
     this.name = name;
     this.size = size;
     this.rarity = rarity;
     this.cost = cost;



    }
}


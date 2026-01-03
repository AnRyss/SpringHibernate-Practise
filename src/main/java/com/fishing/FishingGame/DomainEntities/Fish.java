package com.fishing.FishingGame.DomainEntities;

import com.fishing.FishingGame.ENUMS.Fish_Rarity;
import jakarta.persistence.Embeddable;

@Embeddable
public record Fish(
        String name,
        double size,
        Fish_Rarity rarity,
        double cost)
{
    public Fish(String name, double size, Fish_Rarity rarity, double cost){

     /*switch (rarity) {
         case LEGENDARY -> this.cost = 200000;
         case UNCOMMON -> this.cost = 1000;
         case COMMON -> this.cost = 500;
         case EPIC -> this.cost = 10000;
     }*/
     this.name = name;
     this.size = size;
     this.rarity = rarity;
     this.cost = cost;



    }
}


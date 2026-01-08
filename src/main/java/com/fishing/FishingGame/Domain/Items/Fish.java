package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.FishRarity;
import com.fishing.FishingGame.enums.ItemType;
import jakarta.persistence.Embeddable;

@Embeddable
public record Fish(
        String name,
        double size,
        FishRarity rarity,
        double cost)
    implements IItem
     {
    public Fish(String name, double size, FishRarity rarity, double cost) {
        this.name = name;
        this.size = size;
        this.rarity = rarity;
        this.cost = cost;


    }

         @Override
         public String getName() {
             return name;
         }

         @Override
         public ItemType getType() {
             return ItemType.Fish;
         }
     }


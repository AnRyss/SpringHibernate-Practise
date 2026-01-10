package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.FishRarity;
import com.fishing.FishingGame.enums.ItemType;
import jakarta.persistence.Embeddable;

import java.util.Objects;

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

         @Override
         public boolean equals(Object o) {
             if (o == null || getClass() != o.getClass()) return false;
             Fish fish = (Fish) o;
             return Double.compare(size, fish.size) == 0 && Double.compare(cost, fish.cost) == 0 && Objects.equals(name, fish.name) && rarity == fish.rarity;
         }

         @Override
         public int hashCode() {
             return Objects.hash(name, size, rarity, cost);
         }
     }


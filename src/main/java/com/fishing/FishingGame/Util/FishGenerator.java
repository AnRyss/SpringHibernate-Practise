package com.fishing.FishingGame.Util;

import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.ENUMS.Fish_Rarity;
import com.fishing.FishingGame.ENUMS.Fish_Type;

import java.util.*;

public  class FishGenerator implements IGenerator {
    private static Fish_Rarity pickTheWinner() {
        List<Long> chances = new ArrayList<>();
        for (Fish_Rarity rarity : Fish_Rarity.values()) {
            chances.add(rarity.getChance());
        }
        Long[] normilizedChances = LuckService.normalizeChances(chances.toArray(new Long[]{}));
        double randomValue = Math.random();

        double currentSum = 0.0;
        for (int i = 0; i < normilizedChances.length; i++) {
            currentSum += normilizedChances[i];
            if (randomValue <= currentSum) {
                return Fish_Rarity.values()[i];
            }

        }
        return Fish_Rarity.values()[0];
    }

    public static Fish generate() {
        Fish_Rarity rarity = pickTheWinner();
        List<Fish_Type> potentialFishList = new ArrayList<>();
        for (Fish_Type type : Fish_Type.values()) {
            if (type.getRarity().equals(rarity))
                potentialFishList.add(type);
        }
        int randomIndex = Math.toIntExact(
                Math.round(
                        Math.random() * potentialFishList.size()
                ));
        return potentialFishList.get(randomIndex).createFish();

    }
}

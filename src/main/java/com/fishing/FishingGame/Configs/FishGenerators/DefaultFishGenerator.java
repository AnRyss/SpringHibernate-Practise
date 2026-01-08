package com.fishing.FishingGame.Configs.FishGenerators;

import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Dto.FishingContext;
import com.fishing.FishingGame.Interfaces.IFishGenerator;
import com.fishing.FishingGame.Util.LuckService;
import com.fishing.FishingGame.enums.FishRarity;
import com.fishing.FishingGame.enums.FishType;
import org.springframework.stereotype.Component;

import java.util.*;
@Component("default")
public  class DefaultFishGenerator implements IFishGenerator {
    private FishRarity pickTheWinner() {
        List<Long> chances = new ArrayList<>();
        for (FishRarity rarity : FishRarity.values()) {
            chances.add(rarity.getChance());
        }
        Long[] normilizedChances = LuckService.normalizeChances(chances.toArray(new Long[]{}));
        double randomValue = Math.random();

        double currentSum = 0.0;
        for (int i = 0; i < normilizedChances.length; i++) {
            currentSum += normilizedChances[i];
            if (randomValue <= currentSum) {
                return FishRarity.values()[i];
            }

        }
        return FishRarity.values()[0];
    }
    @Override
    public Fish generate(FishingContext context) {
        context = null;
        FishRarity rarity = pickTheWinner();
        List<FishType> potentialFishList = new ArrayList<>();
        for (FishType type : FishType.values()) {
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

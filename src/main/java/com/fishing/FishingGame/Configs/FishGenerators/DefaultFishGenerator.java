package com.fishing.FishingGame.Configs.FishGenerators;

import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Dto.FishingContext;
import com.fishing.FishingGame.Interfaces.IFishGenerator;
import com.fishing.FishingGame.Util.LuckUtil;
import com.fishing.FishingGame.enums.FishRarity;
import com.fishing.FishingGame.enums.FishType;
import org.springframework.stereotype.Component;

import java.util.*;
@Component("default")
public  class DefaultFishGenerator implements IFishGenerator {
    @Override
    public Fish generate(FishingContext context) {
        FishRarity rarity = LuckUtil.pickTheWinner(context.location());
        List<FishType> potentialFishList = new ArrayList<>();
        for (FishType type : FishType.values()) {
            if (type.getRarity().equals(rarity))
                potentialFishList.add(type);
        }
        int randomIndex = (int) (Math.random() * potentialFishList.size());
        return potentialFishList.get(randomIndex).createFish();

    }
}

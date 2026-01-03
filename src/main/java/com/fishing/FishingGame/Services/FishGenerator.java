package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.ENUMS.FISHRARITY;

import java.util.Map;
import java.util.Random;

public final class FishGenerator implements IGenerator {
    private final Random random = new Random();
    private final Double[] chances = {2.0,50.0,100.0,130.0};
    //private final Double[] normilizedChances = LuckService.normalizeChances();
    private final Map<FISHRARITY, Double> rarityWeights = Map.of(
            FISHRARITY.COMMON, 0.5,
            FISHRARITY.UNCOMMON, 0.3,
            FISHRARITY.EPIC, 0.04,
            FISHRARITY.LEGENDARY, 0.01
    );
    @Override
    public Object generate() {
        return null;
    }
}

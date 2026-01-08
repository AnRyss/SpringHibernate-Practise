package com.fishing.FishingGame.Domain.FishLocations;

import com.fishing.FishingGame.enums.FishType;

import java.util.List;

public abstract class AbstractLocation {
    private final String name;
    private final List<FishType> FishThatCanBeCatched;
    private final int PriceToUnlock;
    protected AbstractLocation(String name, List<FishType> fishThatCanBeCatched, int priceToUnlock) {
        this.name = name;
        FishThatCanBeCatched = fishThatCanBeCatched;
        PriceToUnlock = priceToUnlock;
    }

    public int getPriceToUnlock() {
        return PriceToUnlock;
    }

    public String getName() {
        return name;
    }



    public List<FishType> getFishThatCanBeCatched() {
        return FishThatCanBeCatched;
    }



}

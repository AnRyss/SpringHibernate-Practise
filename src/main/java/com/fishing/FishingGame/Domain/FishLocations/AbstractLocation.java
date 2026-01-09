package com.fishing.FishingGame.Domain.FishLocations;

import com.fishing.FishingGame.enums.FishType;

import java.util.List;

public abstract class AbstractLocation {
    private final int id;
    private final String name;
    private final List<FishType> FishThatCanBeCatched;
    private final int PriceToUnlock;
    protected AbstractLocation(int id, String name, List<FishType> fishThatCanBeCatched, int priceToUnlock) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public List<FishType> getFishThatCanBeCatched() {
        return FishThatCanBeCatched;
    }



}

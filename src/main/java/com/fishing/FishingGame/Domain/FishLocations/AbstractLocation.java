package com.fishing.FishingGame.Domain.FishLocations;

import com.fishing.FishingGame.Domain.Items.NotPhysicalItem;
import com.fishing.FishingGame.enums.FishType;

import java.util.List;

public abstract class AbstractLocation extends NotPhysicalItem {

    private final List<FishType> FishThatCanBeCatched;
    private final int PriceToUnlock;
    protected AbstractLocation(Long id, String name, List<FishType> fishThatCanBeCatched, int priceToUnlock) {
        super.setId(id);
        super.setName(name);
        FishThatCanBeCatched = fishThatCanBeCatched;
        PriceToUnlock = priceToUnlock;
    }

    public int getPriceToUnlock() {
        return PriceToUnlock;
    }

    public List<FishType> getFishThatCanBeCatched() {
        return FishThatCanBeCatched;
    }



}

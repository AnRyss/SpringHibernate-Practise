package com.fishing.FishingGame.Domain.FishLocations;

import com.fishing.FishingGame.enums.FishType;

import java.util.ArrayList;
import java.util.List;

public class VoidLocation extends AbstractLocation {
    public VoidLocation() {
        List<FishType> fishThatCanBeCatched = new ArrayList<>();
        fishThatCanBeCatched.add(FishType.CARP);
        super(2, "Пустотная бездна", fishThatCanBeCatched, 0);

    }
}

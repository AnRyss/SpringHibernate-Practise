package com.fishing.FishingGame.Domain.FishLocations;

import com.fishing.FishingGame.enums.FishType;

import java.util.ArrayList;
import java.util.List;

public class StartLocation extends AbstractLocation{

    public StartLocation() {
        List<FishType> fishThatCanBeCatched = new ArrayList<>();
        fishThatCanBeCatched.add(FishType.CARP);
        super("Начальная речка", fishThatCanBeCatched,0);

    }
}

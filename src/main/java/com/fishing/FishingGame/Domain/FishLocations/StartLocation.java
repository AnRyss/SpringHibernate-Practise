package com.fishing.FishingGame.Domain.FishLocations;

import com.fishing.FishingGame.enums.FishType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StartLocation extends AbstractLocation{

    public StartLocation() {
        List<FishType> fishThatCanBeCatched = new ArrayList<>();
        fishThatCanBeCatched.add(FishType.CARP);
        super(1,"Начальная речка", fishThatCanBeCatched,0);

    }
}

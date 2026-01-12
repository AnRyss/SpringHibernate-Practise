package com.fishing.FishingGame.Domain.Items.Passes;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.enums.ItemType;

public class LocationPass extends AbstractPass {
    private final AbstractLocation location;

    public LocationPass(AbstractLocation location) {
        this.location = location;

    }

    @Override
    public String getName() {

        return "Проход на локацию " + super.getName();
    }


}

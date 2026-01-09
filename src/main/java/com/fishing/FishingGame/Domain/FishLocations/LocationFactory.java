package com.fishing.FishingGame.Domain.FishLocations;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public final class LocationFactory {
    private final Map<Integer, AbstractLocation> locations;

    public LocationFactory(List<AbstractLocation> locationList) {
        this.locations = locationList.stream()
                .collect(Collectors.toMap(AbstractLocation::getId, l -> l));
    }
    public AbstractLocation getLocation(int id) {
        AbstractLocation location = locations.get(id);
        if (location == null) {
            throw new IllegalArgumentException("Локация с ID " + id + " не найдена");
        }
        return location;
    }
}

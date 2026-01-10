package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.FishLocations.LocationFactory;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Exceptions.ItemNotInInventoryException;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final PlayerService playerService;
    private final LocationFactory locationFactory;
    public LocationService(PlayerService playerService, LocationFactory locationFactory) {
        this.playerService = playerService;
        this.locationFactory = locationFactory;
    }
    public PlayerDto setCurrentLocation(String userName, int id){
        AbstractLocation location = locationFactory.getLocation(id);
        Player playerDomain = playerService.getDomainByUsername(userName);
        if (!(playerDomain.getPassesInventory().hasAccessTo(location.getName())))
            throw new ItemNotInInventoryException("У игрока нет пропуска " + location.getName());
        playerDomain.setCurrentLocation(locationFactory.getLocation(id));
        return playerService.updatePlayer(userName,playerDomain);
    }
}

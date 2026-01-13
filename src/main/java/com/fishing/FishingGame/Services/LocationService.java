package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.FishLocations.LocationFactory;
import com.fishing.FishingGame.Domain.Items.Passes.LocationPass;
import com.fishing.FishingGame.Domain.Items.Passes.PassFactory;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.exceptions.ItemNotInInventoryException;
import com.fishing.FishingGame.exceptions.WronNamingException;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final PlayerService playerService;
    private final LocationFactory locationFactory;
    private final PassFactory passFactory;
    public LocationService(PlayerService playerService, LocationFactory locationFactory, PassFactory passFactory) {
        this.playerService = playerService;
        this.locationFactory = locationFactory;
        this.passFactory = passFactory;
    }
    public PlayerDto setCurrentLocation(int id){
        AbstractLocation location = locationFactory.getLocation(id);
        Player playerDomain =  playerService.getCurrentPlayer();
        if (!(passFactory.getPass(location.getName()) instanceof LocationPass pass))
            throw new WronNamingException("Неверно назван пропуск (название локации = название пропуска");
        if (!(playerDomain.hasPass(pass)))
            throw new ItemNotInInventoryException("У игрока нет пропуска " + location.getName());
        playerDomain.setCurrentLocation(locationFactory.getLocation(id));
        return playerService.updatePlayer(playerDomain);
    }
}

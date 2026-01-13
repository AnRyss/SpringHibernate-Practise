package com.fishing.FishingGame.Dto;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Domain.PlayerInventory;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Interfaces.IItem;

import java.util.List;

public record PlayerDto(
        Double luck,
        Rod currentRod,
        AbstractLocation currentLocation,
        Double money,
        List<IItem> inventory
) {

}

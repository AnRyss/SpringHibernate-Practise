package com.fishing.FishingGame.Dto;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Domain.Items.IItem;
import com.fishing.FishingGame.Domain.PlayerInventory;

import java.util.List;

public record PlayerDto(
        Double luck,
        Double money,
        PlayerInventory inventory
) {

}

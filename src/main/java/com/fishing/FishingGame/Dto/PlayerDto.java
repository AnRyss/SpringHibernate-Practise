package com.fishing.FishingGame.Dto;

import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Domain.PlayerInventory;
import com.fishing.FishingGame.Entities.PlayerEntity;

import java.util.List;

public record PlayerDto(
        double luck,
        Rod rod,
        double money,
        PlayerInventory playerInventory
) {

}

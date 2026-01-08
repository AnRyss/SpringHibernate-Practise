package com.fishing.FishingGame.Dto;

import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Entities.PlayerEntity;

import java.util.List;

public record PlayerDto(
        double luck,
        Rod rod,
        double money,
        List<Fish> FishInventory
) {
    public PlayerDto(Player player) {
        this(player.getLuck(), player.getRod(), player.getMoney(), player.getFishInventory());

    }

    public PlayerDto(PlayerEntity player) {
        this(player.getLuck(), player.getRod(), player.getMoney(), player.getFishInventory());

    }
}

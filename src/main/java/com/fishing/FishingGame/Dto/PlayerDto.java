package com.fishing.FishingGame.Dto;

import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.DomainEntities.Player;
import com.fishing.FishingGame.DomainEntities.Rod;
import com.fishing.FishingGame.Entities.PlayerEntity;

import java.util.List;
import java.util.UUID;

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

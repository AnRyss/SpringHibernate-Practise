package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Services.PlayerService;
import com.fishing.FishingGame.enums.RodTier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/profiles/inventory")
@RestController
public class InventoryController {
    private final PlayerService playerService;

    public InventoryController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/current_rod")
    public PlayerDto setCurrentRod(){
     Player player = playerService.getCurrentPlayer();
     Rod rod = new Rod(RodTier.COMMON);
     player.setCurrentRod(rod);
     return playerService.updatePlayer(player);
    }
}

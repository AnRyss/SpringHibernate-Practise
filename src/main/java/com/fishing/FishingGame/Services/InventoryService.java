package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.Mappers.PlayerMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final PlayerService playerService;
    public InventoryService(PlayerService playerService) {
        this.playerService = playerService;
    }
    @Transactional
    public PlayerDto removeItem (int index){
       Player playerDomain =  playerService.getCurrentPlayer();
       playerDomain.getInventory().removeItem(index);
      return playerService.updatePlayer(playerDomain);

    }
   @Transactional
    public PlayerDto addItem( IItem item){
       Player playerDomain = playerService.getCurrentPlayer();
       playerDomain.getInventory().addItem(item);
       return playerService.updatePlayer(playerDomain);
   }
}

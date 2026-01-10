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
    public InventoryService(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
    }
    @Transactional
    public PlayerDto removeItem (String userName, int index){
       Player playerDomain = playerService.getDomainByUsername(userName);
       playerDomain.getInventory().removeItem(index);
      return playerService.updatePlayer(userName,playerDomain);

    }
   @Transactional
    public PlayerDto addItem(String userName, IItem item){
       Player playerDomain = playerService.getDomainByUsername(userName);
       playerDomain.getInventory().addItem(item);
       return playerService.updatePlayer(userName,playerDomain);
   }
}

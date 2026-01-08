package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.Mappers.PlayerMapper;
import com.fishing.FishingGame.enums.RodTier;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ShopService {
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;
    public ShopService(PlayerRepository repository, PlayerMapper playerMapper, PlayerService playerService) {
        this.playerService = playerService;

        this.playerMapper = playerMapper;
    }

    @Transactional
    public Boolean upgradeRod(String userName) {
        Player playerDomain = playerService.getDomainByUsername(userName);
        playerDomain.upgradeRod();
        playerService.updatePlayer(userName,playerDomain);
        return true;
    }

    @Transactional
    public Boolean sellFish(String userName) {
        Player playerDomain = playerService.getDomainByUsername(userName);
        playerDomain.sellFish();
       playerService.updatePlayer(userName,playerDomain);

        return true;
    }

}

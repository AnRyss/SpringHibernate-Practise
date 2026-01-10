package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Mappers.PlayerMapper;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class ShopService {

    private final PlayerService playerService;

    public ShopService(PlayerRepository repository, PlayerMapper playerMapper, PlayerService playerService) {
        this.playerService = playerService;
    }

    @Transactional
    public PlayerDto upgradeRod(String userName) {
        Player playerDomain = playerService.getDomainByUsername(userName);
        playerDomain.upgradeRod();
        playerService.updatePlayer(userName, playerDomain);
        return playerService.updatePlayer(userName, playerDomain);
    }

    @Transactional
    public PlayerDto sellFish(String userName) {
        Player playerDomain = playerService.getDomainByUsername(userName);
        playerDomain.sellFish();
        return playerService.updatePlayer(userName, playerDomain);
    }

}

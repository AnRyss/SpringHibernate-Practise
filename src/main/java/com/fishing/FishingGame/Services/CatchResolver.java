package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Interfaces.IFishGenerator;
import com.fishing.FishingGame.Dto.FishingContext;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;


@Component
public class CatchResolver {
    private final PlayerService playerService;
    public CatchResolver(PlayerRepository repository, ScheduledExecutorService executor, ApplicationContext context, CatchService catchService, PlayerService playerService) {
        this.playerService = playerService;
    }

    @Transactional
    public void finishCatching(String userName,Player player, IFishGenerator generator, FishingContext fishContext) {
        player.getInventory().addItem(generator.generate(fishContext));
        playerService.updatePlayer(userName,player);
    }

}

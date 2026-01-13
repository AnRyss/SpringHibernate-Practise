package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Interfaces.IFishGenerator;
import com.fishing.FishingGame.Dto.FishingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CatchResolver {
    private static final Logger logger = LoggerFactory.getLogger(CatchResolver.class);
    private final PlayerService playerService;
    public CatchResolver( PlayerService playerService) {
        this.playerService = playerService;
    }

    @Transactional
    public void finishCatching(Player player, IFishGenerator generator, FishingContext fishContext) {
        try {
            player.getInventory().addItem(generator.generate(fishContext));
            playerService.updatePlayer(player);
            logger.info("Fish saved to player {}", player.getUuid());
        } catch (Exception e) {
            logger.error("Error saving fish for player: {}", player.getUuid(), e);
            throw e;
        }
    }

}

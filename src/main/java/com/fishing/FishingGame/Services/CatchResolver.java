package com.fishing.FishingGame.Services;

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
    private final PlayerRepository repository;

    public CatchResolver(PlayerRepository repository, ScheduledExecutorService executor, ApplicationContext context, CatchService catchService) {
        this.repository = repository;

    }

    @Transactional
    public void finishCatching(UUID uuid, IFishGenerator generator, FishingContext fishContext) {
        PlayerEntity user = repository.findById(uuid).orElseThrow(() -> new NullPointerException("Invalid user"));
        user.getFishInventory().add(generator.generate(fishContext));
        repository.save(user);
    }

}

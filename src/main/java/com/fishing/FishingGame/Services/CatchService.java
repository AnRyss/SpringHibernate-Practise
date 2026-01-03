package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.ENUMS.Fish_Rarity;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class CatchService {
private final PlayerRepository repository;
private final ScheduledExecutorService executor;

    public CatchService(PlayerRepository repository, PlayerService playerService, PlatformTransactionManager transactionManager,   @Qualifier("fishingScheduler")ScheduledExecutorService executor) {
        this.repository = repository;

        this.executor = executor;
    }

@Transactional
    public CompletableFuture<String> startCatch(UUID uuid) {
        PlayerEntity user = repository.findById(uuid).orElseThrow(() -> new NullPointerException("Invalid user"));
    if (!user.getRod().isFishable()) {
        return CompletableFuture.completedFuture("Рыбалка не началась");
    }

    Integer timetocatch = LuckService.getFishingTime();
    String startMessage = ("Рыбалка началась, ждите: " + timetocatch);
    CompletableFuture<String> fishingResult = new CompletableFuture<>();

       if (user.getRod().isFishable())   {
           executor.schedule(() -> {

                PlayerEntity freshuser = repository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Invalid user"));

                   Fish fish = catchFish();
                   freshuser.getFishInventory().add(fish);
                   repository.save(freshuser);

               fishingResult.complete("Рыба поймана!");

           },(long)timetocatch, TimeUnit.SECONDS);

       };

    return CompletableFuture.completedFuture(startMessage);
    }
 private  Fish catchFish(){
      return new Fish("123",2, Fish_Rarity.EPIC,2);
 }


}

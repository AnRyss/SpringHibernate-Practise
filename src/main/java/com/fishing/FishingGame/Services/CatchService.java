package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import com.fishing.FishingGame.Util.IFishGenerator;
import com.fishing.FishingGame.Util.LuckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class CatchService {
    private final PlayerRepository repository;
    @Autowired
    private final ScheduledExecutorService executor;
    private final Map<UUID, Long> activeFishers = new ConcurrentHashMap<>();

    public CatchService(PlayerRepository repository, PlayerService playerService,  ScheduledExecutorService executor) {
        this.repository = repository;
        this.executor = executor;
    }

    public String startCatch(UUID uuid, IFishGenerator FishGenerator) {
        PlayerEntity user = repository.findById(uuid).orElseThrow(() -> new NullPointerException("Invalid user"));

        if (!user.getRod().isFishable())
            return "Удочка сломана, надо починить";
        if (activeFishers.putIfAbsent(uuid, System.currentTimeMillis()) != null)
            return "Рыбалка уже идет!";

        Integer timetocatch = LuckService.getFishingTime();

        if (user.getRod().isFishable()) {
            executor.schedule(() -> {
                // Можно было сделать через DI EntityManager.unwrap(Session.class).merge(user), но имхо overcoding.
                PlayerEntity freshuser = repository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
                Fish fish = FishGenerator.generate();
                freshuser.getFishInventory().add(fish);
                repository.save(freshuser);
                activeFishers.remove(uuid);


            }, (long) timetocatch, TimeUnit.SECONDS);

        }
        ;

        return ("Рыбалка началась, ждите: " + timetocatch);
    }

    public boolean isPlayerInFishingProcess(UUID uuid) {
        return !(activeFishers.get(uuid) == null);
    }

}

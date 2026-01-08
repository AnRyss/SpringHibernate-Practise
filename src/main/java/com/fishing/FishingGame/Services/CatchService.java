package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.FishingContext;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Mappers.PlayerMapper;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import com.fishing.FishingGame.Interfaces.IFishGenerator;
import com.fishing.FishingGame.Util.LuckService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class CatchService {
    @Value("${game.fishing.generator-type}")
    private String genType;
    private final Map<String, IFishGenerator> generators;
    private final CatchResolver  catchResolver;
    private final ScheduledExecutorService executor;
    private final PlayerService playerService;
    private final Map<String, Long> activeFishers = new ConcurrentHashMap<>();
    public CatchService(PlayerRepository repository, PlayerService playerService, ScheduledExecutorService executor, ApplicationContext context, PlayerRepository repository1, CatchResolver catchResolver, ScheduledExecutorService executor1, Map<String, IFishGenerator> generators, PlayerService playerService1, PlayerMapper playerMapper) {
        this.catchResolver = catchResolver;
        this.executor = executor;
        this.generators = generators;
        this.playerService = playerService1;
    }

    public String startCatch(String userName) {
        Player player = playerService.getDomainByUsername(userName);
        IFishGenerator generator = generators.getOrDefault(genType,generators.get("default"));
        if (!player.getCurrentRod().isFishable())
            return "Удочка сломана, надо починить";
        if (activeFishers.putIfAbsent(userName, System.currentTimeMillis()) != null)
            return "Рыбалка уже идет!";
        Integer timetocatch = LuckService.getFishingTime();
        executor.schedule(() -> {
            try {
                Player freshPlayer = playerService.getDomainByUsername(userName);
                catchResolver.finishCatching(userName,freshPlayer, generator, new FishingContext(null,null,null));
            } finally {
                activeFishers.remove(userName);
            }
        }, (long) timetocatch, TimeUnit.SECONDS);

        return "Рыбалка началась!";
    }

    public boolean isPlayerInFishingProcess(String userName) {
        return !(activeFishers.get(userName) == null);
    }




    public Map<String, Long> getActiveFishers() {
        return activeFishers;
    }
}

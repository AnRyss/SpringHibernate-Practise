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
    private final ApplicationContext context;
    private final PlayerRepository repository;
    private final CatchResolver  catchResolver;
    private final ScheduledExecutorService executor;
    private  final PlayerMapper playerMapper;
    private final Map<UUID, Long> activeFishers = new ConcurrentHashMap<>();

    public CatchService(PlayerRepository repository, PlayerService playerService, ScheduledExecutorService executor, ApplicationContext context, PlayerRepository repository1, CatchResolver catchResolver, ScheduledExecutorService executor1, PlayerMapper playerMapper) {
        this.context = context;
        this.repository = repository;
        this.catchResolver = catchResolver;
        this.executor = executor;
        this.playerMapper = playerMapper;
    }

    public String startCatch(UUID uuid) {
        Player user = playerMapper.toDomain(repository.findById(uuid).orElseThrow(() -> new NullPointerException("Invalid user")));
        IFishGenerator generator = context.getBean(genType, IFishGenerator.class);
        if (!user.getRod().isFishable())
            return "Удочка сломана, надо починить";
        if (activeFishers.putIfAbsent(uuid, System.currentTimeMillis()) != null)
            return "Рыбалка уже идет!";
        Integer timetocatch = LuckService.getFishingTime();
        executor.schedule(() -> {
            try {

                catchResolver.finishCatching(uuid, generator, new FishingContext(null,null,null));
            } finally {
                activeFishers.remove(uuid);
            }
        }, (long) timetocatch, TimeUnit.SECONDS);

        return "Рыбалка началась!";
    }

    public boolean isPlayerInFishingProcess(UUID uuid) {
        return !(activeFishers.get(uuid) == null);
    }




    public Map<UUID, Long> getActiveFishers() {
        return activeFishers;
    }
}

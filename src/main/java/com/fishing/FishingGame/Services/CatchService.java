package com.fishing.FishingGame.Services;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.FishingContext;
import com.fishing.FishingGame.Mappers.PlayerMapper;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import com.fishing.FishingGame.Interfaces.IFishGenerator;
import com.fishing.FishingGame.Util.LuckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(CatchService.class);
    private final Map<UUID, Long> activeFishers = new ConcurrentHashMap<>();
    public CatchService( PlayerService playerService, ScheduledExecutorService executor,  Map<String, IFishGenerator> generators, CatchResolver catchResolver) {
        this.catchResolver = catchResolver;
        this.executor = executor;
        this.generators = generators;
        this.playerService = playerService;


    }

    public String startCatch() {
        Player player = playerService.getCurrentPlayer();
        if (!player.getCurrentRod().isFishable())
            return "Удочка сломана, надо починить";
        if (activeFishers.putIfAbsent(player.getUuid(), System.currentTimeMillis()) != null)
            return "Рыбалка уже идет!";
        Integer timetocatch = LuckUtil.getFishingTime();
        executor.schedule(() -> {
            try {
                logger.info("Schedule started");
                Player freshPlayer = playerService.getDomainByUUID(player.getUuid());
                logger.info("Fresh user was got");
                IFishGenerator generator = generators.getOrDefault(genType,generators.get("default"));
                logger.info("Generator succesfully was got");
                catchResolver.finishCatching(freshPlayer, generator, player.getFishingContext());
                logger.info("Called Catch Resolver from CatchService");
            } finally {
                activeFishers.remove(player.getUuid());
                logger.info("Removed from cache {}",player.getUuid());
            }
        }, (long) timetocatch, TimeUnit.SECONDS);

        return "Рыбалка началась!";
    }

    public boolean isPlayerInFishingProcess(String userName) {
        return !(activeFishers.get(userName) == null);
    }




    public Map<UUID, Long> getActiveFishers() {
        return activeFishers;
    }
}

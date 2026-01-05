package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.ENUMS.Rod_Tier;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.DomainEntities.Rod;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ShopService {
    private final PlayerRepository repository;

    public ShopService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Boolean upgradeRod(UUID playerUuid) {
        double moneyneeded =0;
        PlayerEntity user = repository.findById(playerUuid).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        moneyneeded = user.getRod().getRodtier().getPrice();

        int upgradedenumindex = user.getRod().getRodtier().ordinal()+1;
        Rod roduprgared = new Rod(Rod_Tier.values()[upgradedenumindex]);
        if (moneyneeded > user.getMoney())
            user.setRod(roduprgared);
        return (moneyneeded > user.getMoney());

    }
    @Transactional
    public Boolean sellFish(UUID playerUuid){
        PlayerEntity user = repository.findById(playerUuid).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        if  (user.getFishInventory().isEmpty()) return false;
       double total = user.getFishInventory().stream()
            .mapToDouble(Fish::cost)
            .sum();
    user.setMoney(user.getMoney() + total);
    user.getFishInventory().clear();
    repository.save(user);

        return true;
    }

}

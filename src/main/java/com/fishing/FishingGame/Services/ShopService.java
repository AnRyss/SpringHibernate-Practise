package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.ENUMS.Rod_Tier;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.DomainEntities.Rod;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;
//import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopService {
    private final PlayerRepository repository;

    public ShopService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Boolean upgradeRod(UUID playerUuid) {
        double moneyneeded =0;
        PlayerEntity user = repository.findById(playerUuid).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        switch (user.getRod().getRodtier()){
            case Rod_Tier.COMMON -> moneyneeded = 500;
            case Rod_Tier.UNCOMMON -> moneyneeded = 1500;
            case Rod_Tier.EPIC -> moneyneeded = 5000;
            case LEGENDARY -> moneyneeded = 1500000;
            case ADMIN -> {

            }
        }
        int upgradedenuminex = user.getRod().getRodtier().ordinal()+1;
        Rod roduprgared = new Rod(Rod_Tier.values()[upgradedenuminex]);
        if (moneyneeded > user.getMoney()) user.setRod(roduprgared);
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

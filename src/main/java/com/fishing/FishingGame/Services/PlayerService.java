package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.DomainEntities.Player;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }
@Transactional
    public PlayerDto createProfile(){
        Player pl = Player.Beginner();
        repository.save(new PlayerEntity(pl));
        return new PlayerDto(pl.getUuid());
    }


    public  List<PlayerEntity> getAllProfiles() {
        return repository.findAll();
    }
    public Player getProfileByUUID(UUID id) {
        PlayerEntity entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        return new Player(
                entity.getUuid(),
                entity.getRod(),
                entity.getLuck(),
                entity.getMoney(),
                (List<Fish>) entity.getFishInventory()

        );
    }

}

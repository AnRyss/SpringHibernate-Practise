package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Game.Fish;
import com.fishing.FishingGame.Game.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public void createProfile(){
        PlayerEntity defaulthuman = new PlayerEntity(new Player());
        repository.save(defaulthuman);
    }


    public  List<PlayerEntity> getAllProfiles() {
        return repository.findAll();
    }
    public Player getProfileByUUID(UUID id) {
        PlayerEntity entity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        return new Player(
                entity.getRod(),
                entity.getUuid(),
                entity.getLuck(),
                entity.getMoney(),
                (List<Fish>) entity.getFishInventory()

        );
    }
    public void setProfiles(Map<Integer, Player> profiles) {
       // HumanService.profiles = profiles;
    }
    public String getMyProfile(Player user){
        return user.toString();

    }
}

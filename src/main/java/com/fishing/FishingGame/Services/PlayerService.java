package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.DomainEntities.Player;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import com.fishing.FishingGame.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    public PlayerService(PlayerRepository repository, UserRepository userRepository) {
        this.playerRepository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PlayerDto createProfile() {
        Player pl = Player.Beginner();
        playerRepository.save(new PlayerEntity(pl));
        return new PlayerDto(pl);
    }


    public List<PlayerEntity> getAllProfiles() {
        return playerRepository.findAll();
    }

    @Deprecated
    public PlayerDto getProfileByUUID(UUID id) {
        PlayerEntity entity = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        return new PlayerDto(entity);
    }

    public PlayerDto getProfileByUserName(String username) {
        PlayerEntity entity = userRepository.findProfileByUsername(username).orElseThrow();
        return new PlayerDto(entity);

    }
}

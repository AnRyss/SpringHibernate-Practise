package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Exceptions.PlayerNotFoundException;
import com.fishing.FishingGame.Mappers.PlayerMapper;
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
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository repository, UserRepository userRepository, PlayerMapper playerMapper) {
        this.playerRepository = repository;
        this.userRepository = userRepository;
        this.playerMapper = playerMapper;
    }

    @Transactional
    public PlayerDto createProfile() {
        Player pl = Player.Beginner();
        playerRepository.save(playerMapper.toEntity(pl));
        return playerMapper.toDto(pl);
    }


    public List<PlayerEntity> getAllProfiles() {
        return playerRepository.findAll();
    }


    public PlayerDto getProfileByUUID(UUID id) {
        PlayerEntity entity = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        return playerMapper.toDto(entity);
    }

    public PlayerDto getProfileByUserName(String username) {
        PlayerEntity entity = userRepository.findProfileByUsername(username).orElseThrow();
        return playerMapper.toDto(entity);
    }
    public Player getDomainByUsername(String username) {
        PlayerEntity entity = userRepository.findProfileByUsername(username)
                .orElseThrow(() -> new PlayerNotFoundException(username));
        return playerMapper.toDomain(entity);
    }
    @Transactional
    public PlayerDto updatePlayer(String userName,Player domain) {
        PlayerEntity entity = userRepository.findProfileByUsername(userName)
                .orElseThrow();
        playerMapper.updateEntity(entity, domain);
        playerRepository.save(entity);
        return  playerMapper.toDto(entity);
    }
}

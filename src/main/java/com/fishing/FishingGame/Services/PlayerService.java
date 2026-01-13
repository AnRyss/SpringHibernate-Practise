package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Entities.UserEntity;
import com.fishing.FishingGame.exceptions.PlayerNotFoundException;
import com.fishing.FishingGame.Mappers.PlayerMapper;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import com.fishing.FishingGame.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;
    private final PlayerMapper playerMapper;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    public PlayerService(PlayerRepository repository, UserRepository userRepository, PlayerMapper playerMapper, PasswordEncoder passwordEncoder) {
        this.playerRepository = repository;
        this.userRepository = userRepository;
        this.playerMapper = playerMapper;
        this.passwordEncoder = passwordEncoder;

    }

    private Player createProfile() {
        return Player.Beginner();
    }

    @Transactional
    public void createNewPlayer(String username, String rawPassword) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        PlayerEntity playerEntity = playerMapper.toNewEntity(createProfile());
        playerEntity.setUser(user);
        user.setPlayer(playerEntity);

        userRepository.save(user);
    }

    public List<PlayerEntity> getAllProfiles() {
        return playerRepository.findAll();
    }

    public PlayerDto getProfileByUserName(String username) {
        PlayerEntity entity = playerRepository.findProfileByUserName(username).orElseThrow();
        return playerMapper.toDto(entity);
    }

    public Player getDomainByUsername(String username) {
        PlayerEntity entity = playerRepository.findProfileByUserName(username)
                .orElseThrow(() -> new PlayerNotFoundException(username));
        return playerMapper.toDomain(entity);
    }

    public Player getDomainByUUID(UUID uuid) {
        PlayerEntity entity = playerRepository.findById(uuid)
                .orElseThrow(() -> new PlayerNotFoundException(uuid.toString()));
        logger.info("getDomainByUuid predominization {}", uuid);
        Player player = playerMapper.toDomain(entity);
        logger.info("Domain player from PlayerService was found {}", player.toString());
        return player;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PlayerDto updatePlayer(Player domain) {
        PlayerEntity entity = playerRepository.findById(domain.getUuid()).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        playerMapper.updateEntity(entity, domain);
        playerRepository.save(entity);
        return playerMapper.toDto(entity);
    }

    public PlayerDto getProfileByUUID(UUID id) {
        PlayerEntity entity = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Illegal id"));
        return playerMapper.toDto(entity);
    }

    public Player getCurrentPlayer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Игрок не авторизован");
        }
        String username = authentication.getName();
        return getDomainByUsername(username);
    }
}

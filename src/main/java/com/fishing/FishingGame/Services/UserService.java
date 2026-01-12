package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Dto.UserDto;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Entities.UserEntity;
import com.fishing.FishingGame.Mappers.PlayerMapper;
import com.fishing.FishingGame.Mappers.UserMapper;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import com.fishing.FishingGame.Repositories.UserRepository;
import com.fishing.FishingGame.exceptions.PlayerNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(PlayerMapper playerMapper, UserRepository userRepository, PlayerRepository playerRepository, UserMapper userMapper, PlayerService playerService, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public Boolean isUserExists(String userName) {
        return userRepository.existsByUsername(userName);

    }

    public UserDto findUserByPlayerUuid(UUID uuid) {
        return userMapper.toDto(userRepository.findUserByPlayerUuid(uuid).orElseThrow(() -> new PlayerNotFoundException("Такого пользователя нет")));
    }
}

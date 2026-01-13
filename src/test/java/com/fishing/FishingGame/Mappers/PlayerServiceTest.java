package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import com.fishing.FishingGame.Services.PlayerService;
import com.fishing.FishingGame.exceptions.PlayerNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void getDomainByUUID_ShouldReturnPlayer_WhenPlayerExists() {
        // Arrange
        UUID playerId = UUID.randomUUID();
        PlayerEntity mockEntity = new PlayerEntity();
        mockEntity.setUuid(playerId);
        mockEntity.setLuck(15.0);
        mockEntity.setMoney(100.0);

        Player expectedPlayer = new Player(playerId);
        expectedPlayer.setLuck(15.0);
        expectedPlayer.setMoney(100.0);

        // Настраиваем моки
        when(playerRepository.findById(playerId))
                .thenReturn(Optional.of(mockEntity));
        when(playerMapper.toDomain(mockEntity))
                .thenReturn(expectedPlayer);

        // Act
        Player result = playerService.getDomainByUUID(playerId);

        // Assert
        assertNotNull(result);
        assertEquals(playerId, result.getUuid());
        assertEquals(15.0, result.getLuck(), 0.001);
        assertEquals(100.0, result.getMoney(), 0.001);

        // Проверяем, что методы вызывались
        verify(playerRepository, times(1)).findById(playerId);
        verify(playerMapper, times(1)).toDomain(mockEntity);
    }

    @Test
    void getDomainByUUID_ShouldThrowException_WhenPlayerNotFound() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();

        when(playerRepository.findById(nonExistentId))
                .thenReturn(Optional.empty());

        // Act & Assert
        PlayerNotFoundException exception = assertThrows(
                PlayerNotFoundException.class,
                () -> playerService.getDomainByUUID(nonExistentId)
        );

        assertEquals(nonExistentId.toString(), exception.getMessage());
        verify(playerRepository, times(1)).findById(nonExistentId);
        verify(playerMapper, never()).toDomain(any());
    }

    @Test
    void getDomainByUUID_ShouldLogInformation() {
        // Arrange
        UUID playerId = UUID.randomUUID();
        PlayerEntity entity = new PlayerEntity();
        entity.setUuid(playerId);

        Player player = new Player(playerId);

        when(playerRepository.findById(playerId))
                .thenReturn(Optional.of(entity));
        when(playerMapper.toDomain(entity))
                .thenReturn(player);


        // Act
        Player result = playerService.getDomainByUUID(playerId);

        // Assert
        assertNotNull(result);

    }
}

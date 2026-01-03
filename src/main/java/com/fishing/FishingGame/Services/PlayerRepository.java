package com.fishing.FishingGame.Services;

import com.fishing.FishingGame.Entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<PlayerEntity, UUID> {
}

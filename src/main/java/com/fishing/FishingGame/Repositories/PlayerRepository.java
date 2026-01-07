package com.fishing.FishingGame.Repositories;

import com.fishing.FishingGame.Entities.PlayerEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<@NonNull PlayerEntity, @NonNull UUID> {
}

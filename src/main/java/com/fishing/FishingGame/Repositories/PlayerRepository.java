package com.fishing.FishingGame.Repositories;

import com.fishing.FishingGame.Entities.PlayerEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<@NonNull PlayerEntity, @NonNull UUID> {
    @Query("SELECT u.player FROM UserEntity u WHERE u.username = :username")
    Optional<PlayerEntity> findProfileByUserName(@Param("username") String username);

}

package com.fishing.FishingGame.Repositories;

import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u JOIN FETCH u.player WHERE u.username = :username")
    Optional<UserEntity> findUserByUsername(@Param("username") String username);
    @Query("SELECT u FROM UserEntity u WHERE u.player.uuid = :uuid")
    Optional<UserEntity> findUserByPlayerUuid(@Param("uuid") UUID uuid);
    boolean existsByUsername(String username);
}

package com.fishing.FishingGame.Repositories;

import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u.playerProfile FROM User u WHERE u.username = :username")
    Optional<PlayerEntity> findProfileByUsername(@Param("username") String username);
}

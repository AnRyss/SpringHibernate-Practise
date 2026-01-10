package com.fishing.FishingGame.Entities;
import com.fishing.FishingGame.Domain.Player;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "Users")
@Entity
public class PlayerEntity {
    @Id
    private final UUID uuid;
    private double luck;
    private double money;
    @OneToOne(mappedBy = "player")
    private final UserEntity user;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> inventory = new ArrayList<>();
    public PlayerEntity(Player player, UserEntity user) {
        this.uuid = player.getUuid();
        this.luck = player.getLuck();
        this.money = player.getMoney();
        this.user = user;

    }

    public PlayerEntity(UserEntity user) {
        this.user = user;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }
    public double getMoney() {
        return money;
    }
}

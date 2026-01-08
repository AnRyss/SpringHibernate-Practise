package com.fishing.FishingGame.Entities;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.PlayerInventory;
import com.fishing.FishingGame.Domain.Items.Rod;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "Users")
@Entity
public class PlayerEntity {
    @Id
    private final UUID uuid;
    private double luck;
    private double money;
    @OneToMany(mappedBy = "player")
    private InventoryItemEntity item;
    @OneToOne(mappedBy = "player")
    private final UserEntity user;
    @ElementCollection
    @CollectionTable(
            name = "FishInventory",
            joinColumns = @JoinColumn(name = "uuid")
    )
    private PlayerInventory playerInventory;
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
    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }
    public UUID getUuid() {
        return uuid;
    }
    public double getMoney() {
        return money;
    }
}

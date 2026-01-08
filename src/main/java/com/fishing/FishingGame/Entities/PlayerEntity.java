package com.fishing.FishingGame.Entities;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.PlayerInventory;
import com.fishing.FishingGame.Domain.Items.Rod;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "Users")
@Entity
public class PlayerEntity {
    public PlayerEntity(Player player, UserEntity user) {
        this.uuid = player.getUuid();
        this.luck = player.getLuck();
        this.money = player.getMoney();
        this.rod = new Rod(player.getRod().getRodtier());

        this.user = user;
    }

    @Id
    private final UUID uuid;

    private Rod rod;

    private double luck;

    private double money;
    @OneToMany(mappedBy = "player")
    private InventoryItemEntity item;
    @OneToOne(mappedBy = "player")
    private final UserEntity user;

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    @ElementCollection
    @CollectionTable(
            name = "FishInventory",
            joinColumns = @JoinColumn(name = "uuid")
    )
    private PlayerInventory playerInventory;

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

    public void setMoney(double money) {
        this.money = money;
    }

    public double getLuck() {
        return luck;
    }

    public void setLuck(double luck) {
        this.luck = luck;
    }

    public Rod getRod() {
        return rod;
    }

    public void setRod(Rod rod) {
        this.rod = rod;
    }


}

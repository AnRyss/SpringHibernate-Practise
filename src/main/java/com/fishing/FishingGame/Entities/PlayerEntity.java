package com.fishing.FishingGame.Entities;

import com.fishing.FishingGame.Domain.Player;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "players")
@Entity
public class PlayerEntity {
    @Id
    private UUID uuid;
    private Double luck;
    private Double money;
    @OneToOne(mappedBy = "player")
    private UserEntity user;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemEntity> inventory = new ArrayList<>();
    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "current_rod_id", referencedColumnName = "id")
    private ItemEntity currentRod;
    @Column(name = "current_location_id")
    private Integer currentLocationId;

    public ItemEntity getCurrentRod() {
        return currentRod;
    }

    public void setCurrentRod(ItemEntity currentRod) {
        this.currentRod = currentRod;
    }

    public Integer getCurrentLocationId() {
        return currentLocationId;
    }

    public void setCurrentLocationId(int currentLocationId) {
        this.currentLocationId = currentLocationId;
    }


    public PlayerEntity() {
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

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

    public List<ItemEntity> getInventory() {
        return inventory;
    }

    public void setInventory(List<ItemEntity> inventory) {
        this.inventory = inventory;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

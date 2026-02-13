package com.fishing.FishingGame.Entities;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.Items.IItem;
import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<ItemEntity> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<ItemEntity> equipment) {
        this.equipment = equipment;
    }

    private List<ItemEntity> equipment = new ArrayList<>();


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

    public void addItem(ItemEntity item) {
        if (this.inventory == null) {
            this.inventory = new ArrayList<>();
        }
        this.inventory.add(item);
        item.setPlayer(this);
    }

    private void setInventory(List<ItemEntity> inventory) {
        this.inventory = inventory;
        for (ItemEntity item: inventory){
            item.setPlayer(this);
        }
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

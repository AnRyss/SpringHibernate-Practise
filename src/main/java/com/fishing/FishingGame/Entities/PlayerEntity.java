package com.fishing.FishingGame.Entities;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.Mappers.ItemMapper;
import com.fishing.FishingGame.Mappers.UniversalItemMapper;
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
    @OneToOne(optional = true)
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
    public void syncInventory(List<IItem> domainItems, UniversalItemMapper itemMapper) {
        if (domainItems == null) {
            this.inventory.clear();
            return;
        }

        // 1. Собираем ID предметов, которые должны остаться
        Set<Long> domainIds = domainItems.stream()
                .map(IItem::getId)
                .filter(java.util.Objects::nonNull)
                .collect(Collectors.toSet());

        // 2. Удаляем из коллекции только те сущности, которых больше нет в домене
        // Благодаря orphanRemoval = true, Hibernate сам сделает DELETE этих строк
        this.inventory.removeIf(item -> item.getId() != null && !domainIds.contains(item.getId()));

        // 3. Обновляем существующие или добавляем новые
        for (IItem domain : domainItems) {
            if (domain.getId() != null) {
                // Ищем уже существующий элемент в текущей коллекции
                this.inventory.stream()
                        .filter(existing -> domain.getId().equals(existing.getId()))
                        .findFirst()
                        .ifPresent(existing -> itemMapper.updateEntity(existing, domain));
            } else {
                // Создаем новый, если ID нет
                ItemEntity newEntity = new ItemEntity();
                itemMapper.updateEntity(newEntity, domain);
                newEntity.setPlayer(this);
                this.inventory.add(newEntity);
            }
        }
    }

    public List<ItemEntity> getInventory() {
        return inventory;
    }
    public void addItem(ItemEntity item) {
        if (this.inventory == null) {
            this.inventory = new ArrayList<>();
        }
        this.inventory.add(item);
        item.setPlayer(this); // Это заполнит тот самый UUID в логах
    }

    private void setInventory(List<ItemEntity> inventory) {
        this.inventory = inventory;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

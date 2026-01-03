package com.fishing.FishingGame.Entities;

import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.DomainEntities.Player;
import com.fishing.FishingGame.DomainEntities.Rod;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "Users")
@Entity
public class PlayerEntity {
    public PlayerEntity(Player player) {
        this.uuid = player.getUuid();
        this.luck = player.getLuck();
        this.money = player.getMoney();
        this.rod = new Rod(player.getRod().getRodtier());

     this.FishInventory= player.getFishInventory();
    }

    @Id
    private  UUID uuid;
    private Rod rod;
    private double luck;
    private double money;

    @ElementCollection
    @CollectionTable(
            name = "FishInventory",
            joinColumns = @JoinColumn(name = "uuid")
    )
    private List<Fish> FishInventory = new ArrayList<>();

    public PlayerEntity() {
      this.uuid = UUID.randomUUID();
    }

    public void setUuid(UUID uuid) {
      this.uuid = uuid;
    }


    public UUID getUuid() {
        return uuid;
    }



    public List<Fish> getFishInventory() {
        return FishInventory;
    }

    public void setFishInventory(List<Fish> fishInventory) {
        this.FishInventory = fishInventory;
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

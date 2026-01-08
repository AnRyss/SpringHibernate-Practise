package com.fishing.FishingGame.Domain;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.enums.RodTier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private final UUID uuid;
    private double luck;
    private PlayerInventory inventory;
    private double money;
    private AbstractLocation currentLocation;
    private Rod currentRod;
    public Rod getCurrentRod() {
        return currentRod;
    }

    public void setCurrentRod(Rod currentRod) {
        this.currentRod = currentRod;
    }


    public PlayerInventory getInventory() {
        return inventory;
    }

    public void setInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }
    public AbstractLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(AbstractLocation currentLocation) {
        this.currentLocation = currentLocation;
    }
    public Player(UUID uuid) {
        this.uuid = uuid;
    }
    public static Player Beginner(){
        return new Player(
                UUID.randomUUID(),
                1,
                0,
                new PlayerInventory().addItem(new Rod(RodTier.COMMON))
        );
    }

    public Player( UUID uuid, double luck, double money,PlayerInventory inventory) {

        this.uuid = uuid;
        this.luck = luck;
        this.money = money;
        this.inventory = inventory;
    }


    @Override
public String toString() {
    String sb = "Деньги: " + getMoney() + "\n" +
            "Удочка: " + getInventory().getItem(0).toString() + "\n";
    return sb;
}
public UUID getUuid(){
        return this.uuid;
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


}

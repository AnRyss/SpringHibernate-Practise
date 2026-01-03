package com.fishing.FishingGame.Game;

import com.fishing.FishingGame.ENUMS.RODTIER;
import com.fishing.FishingGame.Entities.PlayerEntity;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private Rod rod;
    private final UUID uuid;
    private double luck;
    private double money;
    private List<Fish> FishInventory = new ArrayList<>();

    public Player() {
        this.uuid = UUID.randomUUID();
        this.rod = new Rod(RODTIER.COMMON);
        this.luck = 1;
        this.money = 0;
    }

    public Player(Rod rod, UUID uuid, double luck, double money, List<Fish> fishInventory) {
        this.rod = rod;
        this.uuid = uuid;
        this.luck = luck;
        this.money = money;
        FishInventory = fishInventory;
    }


    @Override
public String toString() {
    String sb = "Деньги: " + getMoney() + "\n" +
            "Удочка: " + getRod() + "\n" +
            "Рыбы: " + getFishInventory().size();
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

    public Rod getRod() {
        return rod;
    }

    public void setRod(Rod rod) {
        this.rod = rod;
    }

    public double getLuck() {
        return luck;
    }

    public void setLuck(double luck) {
        this.luck = luck;
    }

    public List<Fish> getFishInventory() {
        return this.FishInventory;
    }
    public void removeFish(int index){
        this.FishInventory.remove(index);
    }
    public void addFish(Fish fish){
        this.FishInventory.add(fish);
    }

    public void setFishInventory(List<Fish> fishInventory) {
        this.FishInventory = fishInventory;
    }
}

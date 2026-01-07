package com.fishing.FishingGame.DomainEntities;

import com.fishing.FishingGame.enums.RodTier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private Rod rod;
    private final UUID uuid;
    private double luck;
    private double money;
    private List<Fish> FishInventory = new ArrayList<>();

    public Player(UUID uuid) {

        this.uuid = uuid;
    }
    public static Player Beginner(){
        return new Player(
                UUID.randomUUID(),
                new Rod(RodTier.COMMON),
                1,
                0,
                new ArrayList<Fish>()
        );
    }

    public Player( UUID uuid,Rod rod, double luck, double money, List<Fish> fishInventory) {
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

package com.fishing.FishingGame.Domain;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.FishLocations.LocationFactory;
import com.fishing.FishingGame.Domain.FishLocations.StartLocation;
import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Domain.Items.Passes.AbstractPass;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Dto.FishingContext;
import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.exceptions.InShortOfMoneyException;
import com.fishing.FishingGame.exceptions.RodUpgradeException;
import com.fishing.FishingGame.enums.RodTier;

import java.util.UUID;

public class Player {
    private final UUID uuid;
    private Double luck;
    private PlayerInventory inventory;
    private Double money;
    private AbstractLocation currentLocation;
    private Rod currentRod;
    public FishingContext getFishingContext(){
        return new FishingContext(getCurrentLocation(),this,getLuck());
    }
    public Rod getCurrentRod() {
        return  currentRod;
    }

    public void upgradeRod() {
        RodTier nextTier = getCurrentRod().getRodtier().getNext().orElseThrow(() -> new RodUpgradeException(uuid));
        double moneyNeeded = nextTier.getPrice();
        if (moneyNeeded > getMoney())
            throw new InShortOfMoneyException(uuid, getMoney());
        Rod newRod = new Rod(nextTier);
        getInventory().addItem(newRod);
        getInventory().removeItem(getCurrentRod());
        setCurrentRod(newRod);
        setMoney(getMoney() - moneyNeeded);

    }

    public void sellFish() {
        double totalProfit = inventory.getFishes().stream().mapToDouble(Fish::cost).sum();
        inventory.getItems().removeIf(item -> item instanceof Fish);
        this.money += totalProfit;
    }
    public Boolean hasPass(AbstractPass pass){
        return getInventory().getPasses().contains(pass);
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
        this.money = (double) 0;
        this.inventory = new PlayerInventory();
        this.luck = 1.0;
        this.currentLocation = new StartLocation();
        this.currentLocation = null;
    }

    public static Player Beginner() {
        Rod defRod = new Rod(RodTier.COMMON);
        Player player = new Player(
                UUID.randomUUID(),
                1,
                0,
                new PlayerInventory().addItem(defRod)
        );
        player.setCurrentRod(defRod);
        player.setCurrentLocation(new StartLocation());
        return player;
    }

    private Player(UUID uuid, double luck, double money, PlayerInventory inventory) {

        this.uuid = uuid;
        this.luck = luck;
        this.money = money;
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("UUID: ").append(getUuid()).append("\n");

        // Безопасная работа с money
        if (money != null) {
            sb.append("Деньги: ").append(getMoney()).append("\n");
        } else {
            sb.append("Деньги: не указаны\n");
        }

        // Безопасная работа с luck
        if (luck != null) {
            sb.append("Удача: ").append(getLuck()).append("\n");
        } else {
            sb.append("Удача: не указана\n");
        }

        // Безопасная работа с inventory
        if (getInventory() != null && !getInventory().getItems().isEmpty()) {
            sb.append("Удочка: ").append(getInventory().getItem(0)).append("\n");
        } else {
            sb.append("Удочка: отсутствует\n");
        }

        if (getCurrentLocation() != null) {
            sb.append("Локация: ").append(getCurrentLocation().getName()).append("\n");
        }

        return sb.toString();
    }
    public UUID getUuid() {
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

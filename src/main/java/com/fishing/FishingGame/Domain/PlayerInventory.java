package com.fishing.FishingGame.Domain;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Domain.Items.Passes.AbstractPass;
import com.fishing.FishingGame.Domain.Items.Passes.LocationPass;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Interfaces.IInventory;
import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.ItemType;
import com.fishing.FishingGame.exceptions.ItemNotInInventoryException;
import org.hibernate.boot.internal.Abstract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory implements IInventory {
    private static final Logger log = LoggerFactory.getLogger(PlayerInventory.class);
    private List<IItem> items = new ArrayList<>();
    private List<IItem> equipment = new ArrayList<>();

    public PlayerInventory() {
        this.items = new ArrayList<>();
    }

    public PlayerInventory(List<IItem> items) {
        this.items = items;
    }

    public IItem getEquippedItemByType(ItemType itemType) {
        for (IItem i : equipment) {
            if (i.getType() == itemType) {
           return  i;
            }
        }
        throw new ItemNotInInventoryException("No such equipment");

    }
    public Rod getEquippedRod(){
       return (Rod) getEquippedItemByType(ItemType.Rod);
    }
    public AbstractLocation getEquippedLocation(){
        return (AbstractLocation) getEquippedItemByType(ItemType.Location);
    }
    public Boolean equip(IItem item) {
        if (isItemInInventory(item)) {
            for (IItem i : equipment) {
                if (i.getType() == item.getType()) {
                    unequip(i);

                }
            }

            removeItem(item);
            equipment.add(item);
            return true;
        }
        throw new ItemNotInInventoryException("Cant equip item that not exists in inventory");
    }

    public Boolean unequip(IItem item) {
        if (isEquipped(item)) {
            equipment.remove(item);
            addItem(item);
            return true;
        }
        return false;
    }
    public List<AbstractPass> getEquippedPasses() {
        List<AbstractPass> list = new ArrayList<>();
        for (IItem i : equipment) {
            if (i.getType() == ItemType.Pass) {
                list.add((AbstractPass) i);
            }
        }
        return list;
    }
    public List<IItem> getEquippedItems() {
        return this.equipment;
    }

    public Boolean isEquipped(IItem item) {
        for (IItem i : equipment) {
            if (item.equals(i)) return true;
        }
        return false;
    }

    public List<AbstractPass> getPasses() {
        return items.stream()
                .filter(AbstractPass.class::isInstance)
                .map(AbstractPass.class::cast)
                .toList();
    }

    public Boolean isItemInInventory(IItem item) {
        for (IItem i : items) {
            if (item.equals(i)) return true;
        }
        return false;
    }

    public List<Fish> getFishes() {
        return items.stream()
                .filter(Fish.class::isInstance)
                .map(Fish.class::cast)
                .toList();
    }

    @Override
    public List<IItem> getItems() {
        return items;
    }

    @Override
    public IItem getItem(int index) {
        return items.get(index);
    }

    @Override
    public PlayerInventory setItems(List<IItem> items) {
        this.items = items;
        return this;
    }

    @Override
    public PlayerInventory addItem(IItem item) {
        if (item instanceof AbstractPass) {
           equipment.add(item);
            return this;
        }
        items.add(item);
        return this;
    }

    @Override
    public PlayerInventory removeItem(int index) {
        items.remove(index);
        return this;
    }

    public PlayerInventory removeItem(IItem item) {
        items.remove(item);
        return this;
    }

}

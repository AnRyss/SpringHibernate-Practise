package com.fishing.FishingGame.Domain;

import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Domain.Items.Passes.AbstractPass;
import com.fishing.FishingGame.Domain.Items.Passes.LocationPass;
import com.fishing.FishingGame.Interfaces.IInventory;
import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.enums.ItemType;
import org.hibernate.boot.internal.Abstract;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory implements IInventory {
    private List<IItem> items = new ArrayList<>();
    public PlayerInventory() {
    this.items = new ArrayList<>();
    }

    public PlayerInventory(List<IItem> items) {
        this.items = items;
    }
    public List<AbstractPass> getPasses() {
        return items.stream()
                .filter(AbstractPass.class::isInstance)
                .map(AbstractPass.class::cast)
                .toList();
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
        items.add(item);
        return this;
    }

    @Override
    public PlayerInventory removeItem(int index) {
        items.remove(index);
        return this;
    }
    public PlayerInventory removeItem(IItem item){
        items.remove(item);
        return this;
    }

}

package com.fishing.FishingGame.Domain;

import com.fishing.FishingGame.Interfaces.IInventory;
import com.fishing.FishingGame.Interfaces.IItem;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory implements IInventory {
    private List<IItem> items = new ArrayList<>();


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

}

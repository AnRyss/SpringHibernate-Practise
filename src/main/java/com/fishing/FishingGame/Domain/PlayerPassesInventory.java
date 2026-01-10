package com.fishing.FishingGame.Domain;

import com.fishing.FishingGame.Domain.Items.Passes.AbstractPass;
import com.fishing.FishingGame.Exceptions.IllegalItemTypeException;
import com.fishing.FishingGame.Interfaces.IInventory;
import com.fishing.FishingGame.Interfaces.IItem;

import java.util.List;

public class PlayerPassesInventory implements IInventory {
    private List<IItem> passes;

    @Override
    public List<IItem> getItems() {
        return passes;
    }

    @Override
    public IItem getItem(int index) {
        return passes.get(index);
    }

    @Override
    public IInventory setItems(List<IItem> items) {
        if (!(items instanceof AbstractPass))
            throw new IllegalItemTypeException("Предметы неверного типа");
        this.passes = items;
        return this;
    }

    @Override
    public IInventory addItem(IItem item) {
        if (!(item instanceof AbstractPass))
            throw new IllegalItemTypeException("Предмет неверного типа");
        passes.add(item);
        return this;
    }

    @Override
    public IInventory removeItem(int index) {
        passes.remove(index);
        return this;
    }

    public boolean hasAccessTo(String passName) {
        return passes.stream().anyMatch((a) -> ((AbstractPass) a).getName().equals(a.getName()));

    }
}

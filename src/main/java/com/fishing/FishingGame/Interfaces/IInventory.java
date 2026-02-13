package com.fishing.FishingGame.Interfaces;

import com.fishing.FishingGame.Domain.Items.IItem;

import java.util.List;

public interface IInventory {
    List<IItem> getItems();
    IItem getItem(int index);
    IInventory addItem(IItem item);
    IInventory removeItem(int index);

}

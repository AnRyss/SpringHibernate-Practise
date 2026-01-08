package com.fishing.FishingGame.Interfaces;

import java.util.List;

public interface IInventory {
    List<IItem> getItems();
    IItem getItem(int index);
    IInventory setItems(List<IItem> items);
    IInventory addItem(IItem item);
    IInventory removeItem(int index);

}

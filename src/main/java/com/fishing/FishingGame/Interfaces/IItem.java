package com.fishing.FishingGame.Interfaces;

import com.fishing.FishingGame.enums.ItemType;

import java.util.Map;

public interface IItem {
    Long getId();
        void setId(Long id);
        void setName(String name);
    String getName();
    ItemType getType();

}

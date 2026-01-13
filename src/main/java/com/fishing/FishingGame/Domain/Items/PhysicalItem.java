package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.Interfaces.IItem;


public abstract class PhysicalItem implements IItem {
    private String name;
    private Long id;
    @Override
    public Long getId() {
        return id;
    }
@Override
public void setName(String name){
        this.name = name;
}
    @Override
    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public String getName() {
        return name;
    }


}

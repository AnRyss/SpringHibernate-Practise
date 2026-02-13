package com.fishing.FishingGame.Interfaces;

import com.fishing.FishingGame.enums.ItemType;

import java.util.Map;

public abstract class
IItem{
    private  Long id;
    private String name;
    public IItem(Boolean isPhysical, Long id){
        this.isPhysical = isPhysical;
        this.id = id;
    }
    public IItem(Boolean isPhysical){
        this.isPhysical = isPhysical;
        this.id = null;
    }
    public Long getId(){
        return id;
     }
      protected void setId(Long id){
           this.id = id;
       }
    public void setName(String name){
           this.name = name;
       }
    public String getName(){
          return name;
      };
   public abstract  ItemType getType();
    private final Boolean isPhysical;

}

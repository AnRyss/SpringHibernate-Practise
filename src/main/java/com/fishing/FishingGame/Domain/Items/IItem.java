package com.fishing.FishingGame.Domain.Items;

import com.fishing.FishingGame.enums.ItemType;
import com.fishing.FishingGame.exceptions.ItemNotInInventoryException;

import java.util.List;

public  class  IItem{
    private  Long id;
    private String name;
    private List<ItemAttribute> attributeList;
    public List<ItemAttribute> getAttributeList() {
        return attributeList;
    }
    public ItemAttribute getAttributeByName(String name){
        for (ItemAttribute itemAttribute: attributeList){
            if (itemAttribute.getName().equals(name))
                return itemAttribute;
        }
        throw new ItemNotInInventoryException("No such attribute in this item");
    }
    public void setAttributeList(List<ItemAttribute> attributeList) {
        this.attributeList = attributeList;
    }
    public void addAttribute(ItemAttribute itemAttribute){
        attributeList.add(itemAttribute);
    }

    public IItem(ItemType itemType,Boolean isPhysical, Long id){
        this.isPhysical = isPhysical;
        this.id = id;
      addAttribute(new ItemAttribute().setStringValue(itemType.name()).setName("ItemType"));
    }
    public IItem(ItemType itemType, Boolean isPhysical){
        addAttribute(new ItemAttribute().setStringValue(itemType.name()).setName("ItemType"));
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
   public ItemType  getType(){
       return ItemType.valueOf(getAttributeByName("ItemType").getStringValue());
    };
    private final Boolean isPhysical;

}

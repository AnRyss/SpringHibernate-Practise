package com.fishing.FishingGame.enums;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.Domain.Items.ItemAttribute;
import com.fishing.FishingGame.Domain.Items.IItem;

public enum ItemType {
    Fish(com.fishing.FishingGame.Domain.Items.Fish.class),
    Clothes(null),
    Rod(com.fishing.FishingGame.Domain.Items.Rod.class),
    Attribute(ItemAttribute.class),
    Location(AbstractLocation.class),
    Pass(com.fishing.FishingGame.Domain.Items.Passes.LocationPass.class);
    final Class<?> referencedClass;

    ItemType(Class<?> referencedClass) {
        this.referencedClass = referencedClass;
    }

    @SuppressWarnings("unchecked")
    public <T extends IItem> Class<T> getItemClass() {
        if (referencedClass == null) {
            throw new IllegalStateException(
                    "ItemType " + this + " does not have concrete class"
            );
        }
        return (Class<T>) referencedClass;
    }
}

package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Domain.Items.PhysicalItem;
import com.fishing.FishingGame.Entities.ItemEntity;
import com.fishing.FishingGame.Interfaces.IItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class ItemMapper {

    protected UniversalItemMapper universalMapper;

    protected ItemMapper() {
    }

    public abstract ItemEntity toEntity(IItem domain);




    public abstract void updateEntity(@MappingTarget ItemEntity entity, IItem domain);

    public  IItem toDomain(ItemEntity entity){
        IItem mappedItem = null;
        entity.getPhysical() ? mappedItem = new PhysicalItem() {
        }
      if (entity.getPhysical())
    };
}
package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Domain.Items.ItemAttribute;
import com.fishing.FishingGame.Entities.ItemAttributeEntity;
import com.fishing.FishingGame.Entities.ItemEntity;
import com.fishing.FishingGame.Domain.Items.IItem;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public abstract class ItemMapper {

    @Mapping(target = "player", ignore = true)
    @Mapping(target = "equipped", ignore = true)
    @Mapping(target = "attributeList", source = "attributeList")
     abstract ItemEntity toEntity(IItem domain);

    @Mapping(target = "attributeList", source = "attributeList")
    abstract IItem toDomain(ItemEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player", ignore = true)
    @Mapping(target = "equipped", ignore = true)
    abstract void updateEntity(@MappingTarget ItemEntity entity, IItem domain);

    // Маппинг атрибутов
    abstract  ItemAttributeEntity map(ItemAttribute attribute);
    abstract  ItemAttribute map(ItemAttributeEntity entity);
    @ObjectFactory
    protected IItem createItem(ItemEntity entity) {
        // Явно указываем, какой конструктор вызвать
        return new IItem(
                entity.getType(),
                entity.getPhysical(),
                entity.getId()
        );
    }

}
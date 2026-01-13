package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Entities.ItemEntity;
import com.fishing.FishingGame.Interfaces.IItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {UniversalItemMapper.class})
public abstract class ItemMapper {


    protected  UniversalItemMapper universalMapper;
    @Autowired
    protected ItemMapper(UniversalItemMapper universalMapper) {
        this.universalMapper = universalMapper;
    }


    public ItemEntity toEntity(IItem domain) {
        if (domain == null) return null;
        ItemEntity entity = new ItemEntity();
        updateEntity(entity, domain);
        return entity;
    }


    public void updateEntity(@MappingTarget ItemEntity entity, IItem domain) {
        if (domain == null) return;
        universalMapper.updateEntity(entity, domain);
    }

    public IItem toDomain(ItemEntity entity) {
        return universalMapper.toDomain(entity);
    }
}
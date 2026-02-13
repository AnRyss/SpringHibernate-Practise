package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Domain.FishLocations.LocationFactory;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.PlayerInventory;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.ItemEntity;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Interfaces.IItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UniversalItemMapper.class, LocationFactory.class})
public abstract class PlayerMapper {
    protected UniversalItemMapper itemMapper;
    protected LocationFactory locationFactory;

    public PlayerMapper() {

    }

    @Autowired
    public PlayerMapper(UniversalItemMapper universalItemMapper, LocationFactory locationFactory) {
        this.itemMapper = universalItemMapper;
        this.locationFactory = locationFactory;
    }


    @Mapping(target = "user", ignore = true)
    public abstract void updateEntity(@MappingTarget PlayerEntity entity, Player domain);

    @Mapping(target = "inventory", source = "inventory.items")
    public abstract PlayerDto toDto(Player player);

    @Mapping(target = "inventory", expression = "java(entity.getInventory().stream().map(itemMapper::toDomain).toList())")
    public abstract PlayerDto toDto(PlayerEntity entity);

    public PlayerEntity toNewEntity(Player domain) {
        PlayerEntity playerEntity = new PlayerEntity();
        updateEntity(playerEntity, domain);
        return playerEntity;
    }
    public abstract Player toDomain(PlayerEntity entity);


}
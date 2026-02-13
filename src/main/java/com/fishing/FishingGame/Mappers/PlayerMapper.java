package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Domain.FishLocations.LocationFactory;
import com.fishing.FishingGame.Domain.Items.IItem;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.PlayerInventory;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.ItemEntity;
import com.fishing.FishingGame.Entities.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {LocationFactory.class})
public abstract class PlayerMapper {
    @Autowired
    protected ItemMapper itemMapper;
    protected LocationFactory locationFactory;

    protected List<ItemEntity> mapInventory(PlayerInventory inventory) {
        if (inventory == null || inventory.getItems() == null) {
            return new ArrayList<>();
        }

        List<ItemEntity> itemEntities = new ArrayList<>();
        for (var domain : inventory.getItems()) {
            itemEntities.add(itemMapper.toEntity(domain));
        }
        return itemEntities;
    }


    protected PlayerInventory mapInventoryFromEntity(List<ItemEntity> entities) {
        if (entities == null) return new PlayerInventory();

        List<IItem> domainItems = new ArrayList<>();
        List<IItem> domainEquippedItems = new ArrayList<>();
        for (ItemEntity entity : entities) {
            if (entity.getEquipped())
                domainEquippedItems.add(itemMapper.toDomain(entity));

            domainItems.add(itemMapper.toDomain(entity));
        }
        PlayerInventory playerInventory = new PlayerInventory(domainItems);
        for (IItem item : domainEquippedItems) {
            playerInventory.equip(item);
        }
        return playerInventory;
    }

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    public abstract void updateEntity(@MappingTarget PlayerEntity entity, Player domain);


    public abstract PlayerDto toDto(Player player);

    public abstract PlayerDto toDto(PlayerEntity entity);

    public PlayerEntity toNewEntity(Player domain) {
        PlayerEntity playerEntity = new PlayerEntity();
        updateEntity(playerEntity, domain);
        return playerEntity;
    }

    public abstract Player toDomain(PlayerEntity entity);


}
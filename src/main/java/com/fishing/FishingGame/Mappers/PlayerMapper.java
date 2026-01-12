package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Domain.FishLocations.LocationFactory;
import com.fishing.FishingGame.Domain.Items.Rod;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Domain.PlayerInventory;
import com.fishing.FishingGame.Dto.ItemDto;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.ItemEntity;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Interfaces.IItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class PlayerMapper {

    @Autowired
    protected ItemMapperHelper itemConverter;
    @Autowired
    protected LocationFactory locationFactory;

    // toEntity - оставляем как есть
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "currentLocationId", source = "currentLocation.id")
    @Mapping(target = "currentRod", expression = "java(player.getCurrentRod() != null ? itemConverter.toEntity(player.getCurrentRod()) : null)")
    @Mapping(target = "inventory", ignore = true)
    public abstract PlayerEntity toEntity(Player player);

    // toDomain - убираем expressions
    @Mapping(target = "currentLocation", ignore = true)
    @Mapping(target = "inventory", ignore = true)
    @Mapping(target = "currentRod", ignore = true)
    public abstract Player toDomain(PlayerEntity entity);

    @Mapping(target = "luck", source = "luck")
    @Mapping(target = "money", source = "money")
    @Mapping(target = "currentLocation", expression = "java(locationFactory.getLocation(entity.getCurrentLocationId()))")
    @Mapping(target = "currentRod", expression = "java(entity.getCurrentRod() != null ? (Rod) itemConverter.toDomain(entity.getCurrentRod()) : null)")
    @Mapping(target = "inventory", expression = "java(entity.getInventory().stream().map(itemConverter::toDomain).collect(java.util.stream.Collectors.toList()))")
    public abstract PlayerDto toDto(PlayerEntity entity);

    @Mapping(target = "luck", source = "luck")
    @Mapping(target = "money", source = "money")
    @Mapping(target = "currentLocation", source = "currentLocation")
    @Mapping(target = "currentRod", source = "currentRod")
    @Mapping(target = "inventory", source = "inventory.items")
    public abstract PlayerDto toDto(Player player);


    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "inventory", expression = "java(mapInventoryToEntity(domain.getInventory(), entity))")
    @Mapping(target = "currentLocationId", source = "currentLocation.id")
    @Mapping(target = "currentRod", expression = "java(domain.getCurrentRod() != null ? itemConverter.toEntity(domain.getCurrentRod()) : null)")
    public abstract void updateEntity(@MappingTarget PlayerEntity entity, Player domain);

    // Вспомогательный метод
    protected abstract ItemDto toItemDto(IItem item);

    // AfterMapping для toDomain
    @AfterMapping
    protected void afterToDomain(@MappingTarget Player player, PlayerEntity entity) {
        // Локация
        if (entity.getCurrentLocationId() != null) {
            player.setCurrentLocation(locationFactory.getLocation(entity.getCurrentLocationId()));
        }

        // Инвентарь
        List<IItem> items = entity.getInventory().stream()
                .map(itemConverter::toDomain)
                .collect(Collectors.toList());
        player.setInventory(new PlayerInventory(items));

        // Удочка
        if (entity.getCurrentRod() != null) {
            player.setCurrentRod((Rod) itemConverter.toDomain(entity.getCurrentRod()));
        }
    }


    // AfterMapping для установки связей
    @AfterMapping
    protected void setInventoryRelations(@MappingTarget PlayerEntity entity, Player player) {
        if (player.getInventory() != null) {
            var inventory = player.getInventory().getItems().stream()
                    .map(itemConverter::toEntity)
                    .peek(item -> item.setPlayer(entity))
                    .collect(Collectors.toList());
            entity.getInventory().clear();
            entity.getInventory().addAll(inventory);
        }

        if (entity.getCurrentRod() != null) {
            entity.getCurrentRod().setPlayer(entity);
        }
    }

    @AfterMapping
    protected void afterUpdateEntity(@MappingTarget PlayerEntity entity, Player domain) {
        setInventoryRelations(entity, domain);
    }

    protected List<ItemEntity> mapInventoryToEntity(PlayerInventory inventory, PlayerEntity playerEntity) {
        if (inventory == null || inventory.getItems() == null) {
            return new ArrayList<>();
        }

        return inventory.getItems().stream()
                .map(itemConverter::toEntity)
                .peek(item -> item.setPlayer(playerEntity))
                .collect(Collectors.toList());
    }
}
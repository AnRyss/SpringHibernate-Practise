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

    protected  UniversalItemMapper itemMapper;

    protected  LocationFactory locationFactory;
    @Autowired
    protected PlayerMapper(UniversalItemMapper itemMapper, LocationFactory locationFactory) {
        this.itemMapper = itemMapper;
        this.locationFactory = locationFactory;
    }

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "inventory", ignore = true)
    @Mapping(target = "currentLocationId", source = "currentLocation.id")
    @Mapping(target = "currentRod", ignore = true)
    public abstract void updateEntity(@MappingTarget PlayerEntity entity, Player domain);

    @Mapping(target = "inventory", source = "inventory.items")
    public abstract PlayerDto toDto(Player player);

    @Mapping(target = "currentLocation", expression = "java(locationFactory.getLocation(entity.getCurrentLocationId()))")
    @Mapping(target = "inventory", expression = "java(entity.getInventory().stream().map(itemMapper::toDomain).toList())")
    @Mapping(target = "currentRod", expression = "java(entity.getCurrentRod() != null ? (com.fishing.FishingGame.Domain.Items.Rod) itemMapper.toDomain(entity.getCurrentRod()) : null)")
    public abstract PlayerDto toDto(PlayerEntity entity);

    public PlayerEntity toNewEntity(Player domain) {
        PlayerEntity playerEntity = new PlayerEntity();
        updateEntity(playerEntity, domain);
        return playerEntity;
    }

    public Player toDomain(PlayerEntity entity) {
        if (entity == null) return null;


        List<IItem> domainItems = entity.getInventory().stream()
                .map(itemMapper::toDomain)
                .collect(Collectors.toList());


        Player player = new Player(entity.getUuid());
        player.setLuck(entity.getLuck());
        player.setMoney(entity.getMoney());
        player.setInventory(new PlayerInventory(domainItems));


        if (entity.getCurrentRod() != null) {
            Long rodId = entity.getCurrentRod().getId();
            domainItems.stream()
                    .filter(item -> item instanceof Rod && item.getId().equals(rodId))
                    .findFirst()
                    .map(item -> (Rod) item)
                    .ifPresent(player::setCurrentRod);
        }

        return player;

    }

    @AfterMapping
    protected void linkRelations(@MappingTarget PlayerEntity entity, Player domain) {

        entity.syncInventory(domain.getInventory().getItems(), itemMapper);


        if (domain.getCurrentRod() != null) {
            Long rodId = domain.getCurrentRod().getId();

            ItemEntity rodEntity = entity.getInventory().stream()
                    .filter(item -> item.getId() != null && item.getId().equals(rodId))
                    .findFirst()
                    .orElse(null);

            entity.setCurrentRod(rodEntity);
        } else {
            entity.setCurrentRod(null);
        }
    }
}
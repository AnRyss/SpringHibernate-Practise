package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "id", ignore = true)
    PlayerEntity toEntity(Player domain);
    Player toDomain(PlayerEntity entity);
    PlayerDto toDto(Player domain);
    PlayerDto toDto(PlayerEntity entity);
    void updateEntity(@MappingTarget PlayerEntity entity, Player domain);
}

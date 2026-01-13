package com.fishing.FishingGame.Mappers;
import com.fishing.FishingGame.Domain.Player;
import com.fishing.FishingGame.Dto.UserDto;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "player", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "username", source = "userName")
    UserEntity toEntity(UserDto dto);

    @Mapping(target = "userName", source = "username")
    UserDto toDto(UserEntity entity);
}

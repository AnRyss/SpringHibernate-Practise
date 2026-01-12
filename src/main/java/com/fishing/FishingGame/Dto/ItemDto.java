package com.fishing.FishingGame.Dto;

import com.fishing.FishingGame.enums.ItemType;
import jdk.jshell.Snippet;

public record ItemDto(
        String name,
        ItemType type
) {

}

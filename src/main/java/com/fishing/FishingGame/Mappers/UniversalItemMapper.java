package com.fishing.FishingGame.Mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishing.FishingGame.Entities.ItemEntity;
import com.fishing.FishingGame.Interfaces.IItem;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UniversalItemMapper {
    private final ObjectMapper objectMapper;

    public UniversalItemMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public IItem toDomain(ItemEntity entity) {
        if (entity == null) return null;
        IItem domain = objectMapper.convertValue(entity.getProperties(), entity.getType().getItemClass());
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        return domain;
    }


    public void updateEntity(ItemEntity entity, IItem domain) {
        entity.setName(domain.getName());
        entity.setType(domain.getType());
        System.out.println("Mapping item: " + domain.getType() + " Name: " + domain.getName());

        Map<String, Object> props = objectMapper.convertValue(domain, new TypeReference<Map<String, Object>>() {});
        props.remove("id");
        props.remove("name");
        entity.setProperties(props);
    }
}
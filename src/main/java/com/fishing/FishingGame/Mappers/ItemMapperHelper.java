package com.fishing.FishingGame.Mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishing.FishingGame.Entities.ItemEntity;
import com.fishing.FishingGame.Interfaces.IItem;
import com.fishing.FishingGame.Services.CatchResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemMapperHelper {
    private static final Logger logger = LoggerFactory.getLogger(ItemMapperHelper.class);
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public ItemEntity toEntity(IItem item) {
        if (item == null) return null;

        ItemEntity entity = new ItemEntity();

        entity.setType(item.getType());

        // Конвертируем ВЕСЬ объект в Map
        Map<String, Object> allProperties = objectMapper.convertValue(
                item,
                new TypeReference<Map<String, Object>>() {}
        );

        // Убираем поля, которые уже хранятся отдельно
        allProperties.remove("id");
        allProperties.remove("type");

        entity.setProperties(allProperties);
        return entity;
    }

    public IItem toDomain(ItemEntity entity) {
        if (entity == null) return null;

        // Собираем все свойства вместе
        Map<String, Object> allProperties = new HashMap<>();

        // Добавляем properties из JSON
        if (entity.getProperties() != null) {
            allProperties.putAll(extractProperties(entity));
        }

        // Добавляем базовые поля
        allProperties.put("id", entity.getId());
        allProperties.put("type", entity.getType());

        // Десериализуем в конкретный класс
        return objectMapper.convertValue(
                allProperties,
                entity.getType().getItemClass()
        );
    }

    private Map<String, Object> extractProperties(ItemEntity entity) {
        Object raw = entity.getProperties();

        if (raw == null) return new HashMap<>();

        try {

            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) raw;
            return new HashMap<>(map);

        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}


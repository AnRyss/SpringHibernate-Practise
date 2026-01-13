package com.fishing.FishingGame.Mappers;

import com.fishing.FishingGame.Domain.*;
import com.fishing.FishingGame.Domain.FishLocations.LocationFactory;
import com.fishing.FishingGame.Domain.FishLocations.StartLocation;
import com.fishing.FishingGame.Domain.FishLocations.VoidLocation;
import com.fishing.FishingGame.Domain.Items.*;
import com.fishing.FishingGame.Entities.*;
import com.fishing.FishingGame.enums.ItemType;
import com.fishing.FishingGame.enums.RodTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerMapperTest {

    @Mock
    private ItemMapperHelper itemConverter;

    @Mock
    private LocationFactory locationFactory;
    @InjectMocks
    private PlayerMapperImpl playerMapper;

    @BeforeEach
    void setUp() {
    }


    @Test
    void toEntity_ShouldMapDomainToEntity() {
        // Arrange
        UUID playerId = UUID.randomUUID();


        Rod rod = new Rod(RodTier.COMMON);


        PlayerInventory inventory = new PlayerInventory();
        inventory.addItem(rod);


        Player player = new Player(playerId);
        player.setLuck(12.0);
        player.setMoney(13.0);
        player.setInventory(inventory);
        player.setCurrentRod(rod);


        when(itemConverter.toEntity(any(Rod.class))).thenReturn(createMockRodEntity());
        // Act
        PlayerEntity entity = playerMapper.toEntity(player);
        // LOG
        System.out.println("Detailed PlayerEntity structure:");
        System.out.println(entity.getCurrentRod());
        printAsJson(entity);
        // Assert
        assertNotNull(entity);
        assertEquals(playerId, entity.getUuid());
        assertEquals(12.0, entity.getLuck(), 0.001);
        assertEquals(13.0, entity.getMoney(), 0.001);
        assertNotNull(entity.getCurrentRod());
        assertEquals(1L, entity.getCurrentRod().getId());
        assertEquals(ItemType.Rod, entity.getCurrentRod().getType());
        assertEquals("COMMON удочка", entity.getCurrentRod().getName());
    }

    private ItemEntity createMockRodEntity() {
        ItemEntity entity = new ItemEntity();
        entity.setId(1L);
        entity.setName("COMMON удочка");
        entity.setType(ItemType.Rod);
        return entity;
    }

    @Test
    void toEntity_ShouldHandlePlayerWithoutRod() {
        // Arrange
        UUID playerId = UUID.randomUUID();

        Player player = new Player(playerId);
        player.setLuck(10.0);
        player.setMoney(50.0);
        player.setInventory(new PlayerInventory());
        player.setCurrentRod(null); // Нет удочки

        // Act
        PlayerEntity entity = playerMapper.toEntity(player);
// LOG
        System.out.println("Detailed PlayerEntity structure:");
        printAsJson(entity);
        // Assert
        assertNotNull(entity);
        assertEquals(playerId, entity.getUuid());
        assertEquals(10.0, entity.getLuck(), 0.001);
        assertEquals(50.0, entity.getMoney(), 0.001);
        assertNull(entity.getCurrentRod()); // Удочка должна быть null
    }

    @Test
    void toEntity_ShouldMapInventoryItems() {
        // Arrange
        UUID playerId = UUID.randomUUID();

        Rod rod1 = new Rod(RodTier.COMMON);
        Rod rod2 = new Rod(RodTier.UNCOMMON);

        PlayerInventory inventory = new PlayerInventory();
        inventory.addItem(rod1);
        inventory.addItem(rod2);

        Player player = new Player(playerId);
        player.setLuck(15.0);
        player.setMoney(100.0);
        player.setInventory(inventory);
        player.setCurrentRod(rod1);

        // Настраиваем моки для каждого вызова
        when(itemConverter.toEntity(any(Rod.class)))
                .thenAnswer(invocation -> {
                    Rod r = invocation.getArgument(0);
                    ItemEntity entity = new ItemEntity();
                    entity.setId(r.getRodtier() == RodTier.COMMON ? 1L : 2L);
                    entity.setName(r.getName());
                    entity.setType(ItemType.Rod);
                    return entity;
                });

        // Act
        PlayerEntity entity = playerMapper.toEntity(player);

        // LOG
        System.out.println("Detailed PlayerEntity structure:");
        System.out.println(entity.getCurrentRod());
        printAsJson(entity);

        // Assert
        assertNotNull(entity);
        assertEquals(2, entity.getInventory().size()); // Две удочки в инвентаре
        assertEquals(1L, entity.getCurrentRod().getId()); // Текущая удочка
    }

    @Test
    void toDomain_ShouldMapEntityToDomain() {
        // Arrange
        UUID playerId = UUID.randomUUID();

        PlayerEntity entity = new PlayerEntity();
        entity.setUuid(playerId);
        entity.setLuck(20.0);
        entity.setMoney(200.0);
        entity.setCurrentLocationId(1);


        ItemEntity rodEntity = new ItemEntity();
        rodEntity.setId(1L);
        rodEntity.setName("COMMON удочка");
        rodEntity.setType(ItemType.Rod);
        rodEntity.setProperties(Map.of(
                "rodTier", RodTier.COMMON,
                "durability", 100.0
        ));
        entity.setCurrentRod(rodEntity);


        when(locationFactory.getLocation(1))
                .thenReturn(new StartLocation());

        when(itemConverter.toDomain(rodEntity))
                .thenAnswer(invocation -> {
                    ItemEntity e = invocation.getArgument(0);
                    RodTier tier = (RodTier) e.getProperties().get("rodTier");
                    Double durability = (Double) e.getProperties().get("durability");
                    Rod rod = new Rod(tier);
                    rod.setDurability(durability);
                    return rod;
                });

        // Act
        Player domain = playerMapper.toDomain(entity);
        //Log
        printAsJson(domain);
        System.out.println(domain.getCurrentRod());
        System.out.println(domain.getCurrentLocation().getName());
        // Assert
        assertNotNull(domain);
        assertEquals(playerId, domain.getUuid());
        assertEquals(20.0, domain.getLuck(), 0.001);
        assertEquals(200.0, domain.getMoney(), 0.001);
        assertNotNull(domain.getCurrentRod());
        assertEquals(RodTier.COMMON, domain.getCurrentRod().getRodtier());
        assertEquals(100.0, domain.getCurrentRod().getDurability(), 0.001);
    }

    @Test
    void updateEntity_ShouldUpdateExistingEntity() {
        // Arrange
        UUID playerId = UUID.randomUUID();

        // Существующая Entity
        PlayerEntity existingEntity = new PlayerEntity();
        existingEntity.setUuid(playerId);
        existingEntity.setLuck(10);
        existingEntity.setMoney(50.0);
        existingEntity.setCurrentLocationId(1);

        // Новый Domain объект
        Player updatedPlayer = new Player(UUID.randomUUID());
        updatedPlayer.setMoney(152);
        updatedPlayer.setLuck(2);
        updatedPlayer.setCurrentLocation(new VoidLocation());
        updatedPlayer.setCurrentRod(new Rod(RodTier.LEGENDARY));

        // Настраиваем моки
        when(itemConverter.toEntity(any(Rod.class)))
                .thenAnswer(invocation -> {
                    Rod rod = invocation.getArgument(0);
                    ItemEntity rodEntity = new ItemEntity();
                    rodEntity.setId(1L);
                    rodEntity.setName(rod.getName());
                    rodEntity.setType(ItemType.Rod);
                    return rodEntity;
                });

        // Act
        playerMapper.updateEntity(existingEntity, updatedPlayer);

        // Assert
        assertEquals(playerId, existingEntity.getUuid());
        assertEquals(2, existingEntity.getLuck()); // Обновлено
        assertEquals(152.0, existingEntity.getMoney()); // Обновлено
        assertEquals(2, existingEntity.getCurrentLocationId()); // Обновлено
        assertNotNull(existingEntity.getCurrentRod());
        assertEquals("LEGENDARY удочка", existingEntity.getCurrentRod().getName());
    }

    private void printAsJson(Object obj) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            // Включаем "красивый" отступ
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            System.out.println(json);
        } catch (Exception e) {
            System.out.println("Could not log object: " + e.getMessage());
        }
    }
}

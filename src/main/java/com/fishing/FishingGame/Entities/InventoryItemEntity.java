package com.fishing.FishingGame.Entities;

import com.fishing.FishingGame.enums.ItemType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class InventoryItemEntity {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity player; // Связь с владельцем

}

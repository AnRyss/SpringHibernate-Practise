package com.fishing.FishingGame.Entities;

import com.fishing.FishingGame.enums.ItemType;
import jakarta.persistence.*;
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_category")
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

}

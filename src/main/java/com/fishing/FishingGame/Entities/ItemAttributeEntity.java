package com.fishing.FishingGame.Entities;

import jakarta.persistence.*;

public class ItemAttributeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;
    @Column(name = "attribute_key", nullable = false)
    private String name;
    private Double value;

}

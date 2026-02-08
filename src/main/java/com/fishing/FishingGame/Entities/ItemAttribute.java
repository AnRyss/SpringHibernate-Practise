package com.fishing.FishingGame.Entities;

import jakarta.persistence.*;

public class ItemAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")  // ← понятное имя
    private ItemEntity item;
    @Column(name = "attribute_key", nullable = false)
    private String key;  // "strength", "hook_chance", "durability"

    @Column(name = "value_type")
    private String valueType;  // "INT", "FLOAT", "STRING"

    @Column(name = "int_value")
    private Integer intValue;

    @Column(name = "float_value")
    private Double floatValue;

    @Column(name = "string_value")
    private String stringValue;
    public Object getValue() {
        return switch (valueType) {
            case "INT" -> intValue;
            case "FLOAT" -> floatValue;
            case "STRING" -> stringValue;
            default -> null;
        };
    }
}

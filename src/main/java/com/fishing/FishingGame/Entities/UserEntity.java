package com.fishing.FishingGame.Entities;

import jakarta.persistence.*;

@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne
    @JoinColumn(name = "player_uuid", referencedColumnName = "uuid")
    private PlayerEntity player;
}

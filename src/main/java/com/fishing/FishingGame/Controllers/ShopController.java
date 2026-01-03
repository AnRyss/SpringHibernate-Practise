package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Game.Player;
import com.fishing.FishingGame.Services.PlayerRepository;
import com.fishing.FishingGame.Services.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ShopController {
    private final ShopService shopService;
    private final PlayerRepository repository;

    public ShopController(ShopService shopService, PlayerRepository repository) {
        this.shopService = shopService;
        this.repository = repository;
    }
    @PostMapping("/profile/{player_uuid}/shop/upgrade_rod")
    public ResponseEntity<Boolean> upgradeRod(@PathVariable UUID player_uuid){
       PlayerEntity player = repository.findById(player_uuid).orElseThrow(()-> new IllegalArgumentException("Invalid user"));
      return ResponseEntity.ok(  shopService.upgradeRod(player_uuid));
    }
    @PostMapping("/profile/{player_uuid}/shop/sell_fish")
    public  ResponseEntity<Boolean> sellFish(@PathVariable UUID player_uuid){
        PlayerEntity player = repository.findById(player_uuid).orElseThrow(()-> new IllegalArgumentException("Invalid user"));
        return  ResponseEntity.ok(shopService.sellFish(player_uuid));
    }
}

package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Entities.PlayerEntity;
import com.fishing.FishingGame.Repositories.PlayerRepository;
import com.fishing.FishingGame.Services.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/profiles/shop")
@RestController
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService, PlayerRepository repository) {
        this.shopService = shopService;
    }

    @PostMapping("/upgrade_rod")
    public ResponseEntity<PlayerDto> upgradeRod(@AuthenticationPrincipal UserDetails currentUser) {
        return ResponseEntity.ok(shopService.upgradeRod());
    }

    @PostMapping("/sell_fish")
    public ResponseEntity<PlayerDto> sellFish(@AuthenticationPrincipal UserDetails currentUser){
        return ResponseEntity.ok(shopService.sellFish());
    }
}

package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
@RequestMapping("/profiles")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @PostMapping("/create_new")
    public ResponseEntity<PlayerDto> createProfile(){

        return ResponseEntity.ok(playerService.createProfile());
    }
    @GetMapping("/{uuid}")
    public String getMyProfile(@PathVariable UUID uuid){
        return this.playerService.getProfileByUUID(uuid).toString();

    }
}

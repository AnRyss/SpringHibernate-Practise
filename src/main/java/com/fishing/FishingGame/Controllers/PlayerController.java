package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Dto.PlayerDto;
import com.fishing.FishingGame.Services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping
    public ResponseEntity<PlayerDto> getMyProfile(@AuthenticationPrincipal UserDetails currentUser) {
        return ResponseEntity.ok(playerService.getProfileByUserName(currentUser.getUsername()));

    }

    @GetMapping("/{username}")
    public ResponseEntity<PlayerDto> getPlayerProfileByUserName(@PathVariable String username) {
        return ResponseEntity.ok(playerService.getProfileByUserName(username));
    }
}

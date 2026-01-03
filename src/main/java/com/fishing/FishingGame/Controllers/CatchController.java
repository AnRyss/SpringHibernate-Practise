package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Services.CatchService;
import com.fishing.FishingGame.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class CatchController {
@Autowired
private final CatchService catchservice;
    @Autowired
private final PlayerService playerService;

    public CatchController(CatchService catchservice, PlayerService human) {
        this.catchservice = catchservice;
        this.playerService = human;
    }

    @PostMapping("/profile/create")
    public ResponseEntity<String> createProfile(){
         playerService.createProfile();
         return ResponseEntity.ok("Профиль создан");
    }

    @PostMapping("/profile/{uuid}/catch")
    public ResponseEntity<CompletableFuture<String>> startCatch(@PathVariable("uuid") UUID uuid) {

        return  ResponseEntity.ok(this.catchservice.startCatch(uuid));

    }
    @GetMapping
    public String greetings(){
        return "привет";
    }
    @GetMapping("/profile/{uuid}")
    public String getMyProfile(@PathVariable UUID uuid){
        return this.playerService.getProfileByUUID(uuid).toString();

    }
}

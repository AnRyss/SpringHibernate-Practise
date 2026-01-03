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
private final CatchService catchservice;
    public CatchController(CatchService catchservice, PlayerService human) {
        this.catchservice = catchservice;

    }
    @PostMapping("/profile/{uuid}/catch")
    public ResponseEntity<CompletableFuture<String>> startCatch(@PathVariable("uuid") UUID uuid) {
        return  ResponseEntity.ok(this.catchservice.startCatch(uuid));
    }
    @GetMapping
    public String greetings(){
        return "привет";
    }
}

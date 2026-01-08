package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Services.CatchService;
import com.fishing.FishingGame.Services.PlayerService;
import com.fishing.FishingGame.Configs.FishGenerators.DefaultFishGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("profiles/{uuid}")
@RestController
public class CatchController {
    private final CatchService catchservice;

    public CatchController(CatchService catchservice, PlayerService human) {
        this.catchservice = catchservice;

    }

    @PostMapping("/catch")
    public ResponseEntity<String> startCatch(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(this.catchservice.startCatch(uuid));
    }

}

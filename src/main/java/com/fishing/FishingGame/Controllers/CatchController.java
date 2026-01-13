package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Services.CatchService;
import com.fishing.FishingGame.Services.PlayerService;
import com.fishing.FishingGame.Configs.FishGenerators.DefaultFishGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/profiles") // Убрали {uuid}
@RestController
public class CatchController {
    private final CatchService catchService;

    public CatchController(CatchService catchService) {
        this.catchService = catchService;
    }

    @PostMapping("/catch")
    public ResponseEntity<String> startCatch(@AuthenticationPrincipal UserDetails currentUser) {
        return ResponseEntity.ok(catchService.startCatch());
    }
}
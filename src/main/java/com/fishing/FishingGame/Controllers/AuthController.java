package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Dto.AuthRequest;
import com.fishing.FishingGame.Repositories.UserRepository;
import com.fishing.FishingGame.Services.PlayerService;
import com.fishing.FishingGame.Services.Security.JwtService;
import com.fishing.FishingGame.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserService userService;
    private final PlayerService playerService;
    public AuthController(JwtService jwtService, AuthenticationManager authManager, UserRepository userRepository, UserService userService, PlayerService playerService) {
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.userService = userService;
        this.playerService = playerService;
    }
    @GetMapping
    public String Greet(){
        return "Привет авторизируйся и играй!";
    }
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );


        return jwtService.generateToken(request.username());
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request){
        if (userService.isUserExists(request.username())) {
            return ResponseEntity.badRequest().body("Ошибка: Пользователь с таким именем уже есть!");
        }
    playerService.createNewPlayer(request.username(),request.password());
        return ResponseEntity.ok("Вы зарегистрировались");
    }
}

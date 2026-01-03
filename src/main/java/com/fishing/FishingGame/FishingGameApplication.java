package com.fishing.FishingGame;

import com.google.errorprone.annotations.RestrictedApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;

@SpringBootApplication
public class FishingGameApplication {
	public static void main(String[] args) {
		SpringApplication.run(FishingGameApplication.class, args);
	}

}

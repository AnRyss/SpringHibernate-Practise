package com.fishing.FishingGame.Controllers;

import com.fishing.FishingGame.Dto.ErrorResponse;
import com.fishing.FishingGame.exceptions.RodUpgradeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RodUpgradeException.class)
    public ResponseEntity<ErrorResponse> handleRodUpgrade(RodUpgradeException ex) {
        com.fishing.FishingGame.Dto.ErrorResponse error = new  com.fishing.FishingGame.Dto.ErrorResponse (
                "Ошибка улучшения удочки для игрока: " + ex.getPlayerUuid(),
                "ROD_UPGRADE_ERROR",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

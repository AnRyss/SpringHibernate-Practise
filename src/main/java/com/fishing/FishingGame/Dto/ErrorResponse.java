package com.fishing.FishingGame.Dto;

public record ErrorResponse(
        String message,
        String errorCode,
        long timestamp
) {}
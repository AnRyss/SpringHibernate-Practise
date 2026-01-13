package com.fishing.FishingGame.exceptions;

public class IllegalMultipleRequest extends RuntimeException {
    public IllegalMultipleRequest(String message) {
        super(message);
    }
}

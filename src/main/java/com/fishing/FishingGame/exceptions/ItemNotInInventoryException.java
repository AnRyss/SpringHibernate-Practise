package com.fishing.FishingGame.exceptions;

public class ItemNotInInventoryException extends RuntimeException {
    public ItemNotInInventoryException(String message) {
        super(message);
    }
}

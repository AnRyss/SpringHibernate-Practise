package com.fishing.FishingGame.Exceptions;

public class ItemNotInInventoryException extends RuntimeException {
    public ItemNotInInventoryException(String message) {
        super(message);
    }
}

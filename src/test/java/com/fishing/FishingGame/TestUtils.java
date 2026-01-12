package com.fishing.FishingGame;


import org.springframework.test.util.ReflectionTestUtils;

public class TestUtils {

    public static void setField(Object target, String fieldName, Object value) {
        ReflectionTestUtils.setField(target, fieldName, value);
    }

    public static Object getField(Object target, String fieldName) {
        return ReflectionTestUtils.getField(target, fieldName);
    }
}
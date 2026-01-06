package com.fishing.FishingGame.Util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

public class LuckService {
    public static Integer getFishingTime(){
        Random random = new Random();
        int min = 5, max = 10;
        return  random.nextInt(min,max+1);
    }

    public static <T extends Number> Long[] normalizeChances (T[] chances) {
        long chancessumm = 0;
        for (T chance : chances) {
            chancessumm += chance.longValue();
        }
        ArrayList<Long> normilized = new ArrayList<>();
        for (T chance : chances) {
            normilized.add(chance.longValue() / chancessumm);
        }
        Long[] array = new Long[chances.length];
        return normilized.toArray(array);

    }
}



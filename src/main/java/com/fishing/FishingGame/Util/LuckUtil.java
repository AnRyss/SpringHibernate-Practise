package com.fishing.FishingGame.Util;
import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import com.fishing.FishingGame.enums.FishRarity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LuckUtil {
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
    public static FishRarity pickTheWinner(AbstractLocation location) {
        List<Long> chances = new ArrayList<>();
        for (FishRarity rarity : FishRarity.values()) {
            chances.add(rarity.getChance());
        }
        Long[] normilizedChances = LuckUtil.normalizeChances(chances.toArray(new Long[]{}));
        double randomValue = Math.random();

        double currentSum = 0.0;
        for (int i = 0; i < normilizedChances.length; i++) {
            currentSum += normilizedChances[i];
            if (randomValue <= currentSum) {
                return FishRarity.values()[i];
            }

        }
        return FishRarity.values()[0];
    }
}



package com.fishing.FishingGame.Util;

import com.fishing.FishingGame.DomainEntities.Fish;
import com.fishing.FishingGame.ENUMS.Fish_Rarity;
import com.fishing.FishingGame.Services.LuckService;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public final class FishGenerator implements IGenerator {
    private final Random random = new Random();
    private static final Double[] chances = {2.0,50.0,100.0,130.0};
    private static final Double[] normilizedChances = LuckService.normalizeChances(chances);
private static int pickTheWinner(){
    // 1. Генерируем случайное число от 0.0 до 1.0
    double randomValue = Math.random();
    // 2. Накапливаем сумму, чтобы найти нужный интервал
    double currentSum = 0.0;
    for (int i = 0; i < normilizedChances.length; i++) {
        currentSum += normilizedChances[i];
        // Как только накопленная сумма превысила случайное число — мы нашли победителя
        if (randomValue <= currentSum) {
            return i;
        }

    }
    return normilizedChances.length - 1;
}
    public static Fish generate() {

    }
    public static void main (String[] args){
        System.out.println(Arrays.toString(normilizedChances));
    }
}

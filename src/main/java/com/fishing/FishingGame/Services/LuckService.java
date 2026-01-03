package com.fishing.FishingGame.Services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
@Service
public class LuckService {
    public static Integer getFishingTime(){
        Random random = new Random();
        int min = 5, max = 10;
        return  random.nextInt(min,max+1);
    }

    public static String Luck (int fishattempts, Double chancetocatch) {
        for (int i = 0; i<= fishattempts; i++) {
            Double rand = (Double) Math.random();

            double chance = 1 * chancetocatch;
            if (rand <= chance) return "Поймал";
        }
        return "А нет";

    }
    public static String LuckingIteration (Double chancetocatch, int iterations) {
        int i = 0;
        int sucattempt= 0;
        while (i < iterations)  {
            i++;
            sucattempt++;
            Double rand = (Double) Math.random();

            if (rand <= chancetocatch) {
                return "Поймана на " + sucattempt + " попытке.";

            }
        }
        return "Не поймана";

    }
    public static Double[] normalizeChances (Double[] chances) {
        double chancessumm = 0;
        for (Double chance : chances) {
            chancessumm += chance;
        }
        ArrayList<Double> normilized = new ArrayList<Double>();
        for (Double chance : chances) {
            normilized.add(chance / chancessumm);
        }
        Double[] array = new Double[chances.length];
        return normilized.toArray(array);

    }
}



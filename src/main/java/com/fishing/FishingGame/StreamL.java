package com.fishing.FishingGame;

import com.fishing.FishingGame.Services.LuckService;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class StreamL {
    public static void main(String[] args) {
        int[] array = {2,3,1,1,4};


    }

    public int jump(int[] nums) {
        int l = nums.length;
        if (l <= 1) return 0;

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < l - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                if (currentEnd >= l - 1) break;
            }
        }
        return jumps;
    }
}

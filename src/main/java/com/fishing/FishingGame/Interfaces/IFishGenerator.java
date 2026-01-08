package com.fishing.FishingGame.Interfaces;

import com.fishing.FishingGame.Domain.Items.Fish;
import com.fishing.FishingGame.Dto.FishingContext;
import org.springframework.stereotype.Component;

@Component
public interface IFishGenerator {
   Fish generate(FishingContext context);

}
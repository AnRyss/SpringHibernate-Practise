package com.fishing.FishingGame.Domain.Items.Passes;

import com.fishing.FishingGame.Domain.FishLocations.AbstractLocation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PassFactory {
    private final Map<String, AbstractPass> passes;

    public PassFactory(List<AbstractPass> PassesList) {
        this.passes = PassesList.stream()
                .collect(Collectors.toMap(AbstractPass::getName, l -> l));

    }
    public AbstractPass getPass(String name){
        AbstractPass pass = passes.get(name);
        if (pass == null) {
            throw new IllegalArgumentException("Доступ с именем " + name + " не найден");
        }
        return pass;
    }
}

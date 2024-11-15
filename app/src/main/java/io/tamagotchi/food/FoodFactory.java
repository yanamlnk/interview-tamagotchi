package io.tamagotchi.food;

import io.tamagotchi.TamagotchiException;

public class FoodFactory {
    public static Food create(String name) throws TamagotchiException {
        return switch (name) {
            case "burger" -> new Burger();
            case "fish" -> new Fish();
            case "potato" -> new Potato();
            default -> throw new TamagotchiException("Unknown food: " + name);
        };
    }
}

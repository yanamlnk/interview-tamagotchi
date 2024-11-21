package io.tamagotchi.food;

import io.tamagotchi.TamagotchiException;

/**
 * Factory class for creating instances of different types of food in the Tamagotchi game.
 */
public class FoodFactory {

    /**
     * Creates a Food object based on its name.
     *
     * @param name The name of the food item to create.
     * @return A new instance of the food item.
     * @throws TamagotchiException If the food item name is not recognized.
     */
    public static Food create(String name) throws TamagotchiException {
        return switch (name) {
            case "burger" -> new Burger();
            case "fish" -> new Fish();
            case "potato" -> new Potato();
            default -> throw new TamagotchiException("Unknown food: " + name);
        };
    }
}

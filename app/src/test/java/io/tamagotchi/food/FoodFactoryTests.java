package io.tamagotchi.food;

import io.tamagotchi.TamagotchiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodFactoryTests {
    @Test
    void testCreateValidFood() throws TamagotchiException {
        Food burger = FoodFactory.create("burger");
        assertInstanceOf(Burger.class, burger, "Burger instance should be created.");

        Food fish = FoodFactory.create("fish");
        assertInstanceOf(Fish.class, fish, "Fish instance should be created.");

        Food potato = FoodFactory.create("potato");
        assertInstanceOf(Potato.class, potato, "Potato instance should be created.");
    }

    @Test
    void testCreateInvalidFood() {
        assertThrows(TamagotchiException.class, () -> FoodFactory.create("unknown"), "Should throw exception for unknown food.");
        try {
            FoodFactory.create("unknown");
        } catch (TamagotchiException e) {
            assertEquals("Unknown food: unknown", e.getMessage());
        }
    }

}

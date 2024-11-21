package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.food.Food;

/**
 * Represents a Tamagotchi pet in the Tamagotchi game.
 * This interface defines the basic actions that a Tamagotchi pet can perform.
 */
public interface Tamagotchi {

    /**
     * Makes the Tamagotchi pet eat the specified food.
     *
     * @param food the food to be eaten by the pet
     */
    void eat(Food food);

    /**
     * Decreases the health of the Tamagotchi pet by the specified health value.
     *
     * @param health the health value to be subtracted from the pet's health
     */
    void looseHealth(float health);

    /**
     * Increases the experience points (XP) of the Tamagotchi pet by the specified value.
     *
     * @param xp the experience points to be added to the pet's experience
     */
    void gainXp(float xp);

    /**
     * Increases the money of the Tamagotchi pet by the specified value.
     *
     * @param money the money to be added to the pet's total money
     */
    void gainMoney(float money);

    /**
     * Sets the level of the Tamagotchi pet based on its current experience points (XP).
     */
    void setLevel();

    /**
     * Decreases the money of the Tamagotchi pet by the specified value.
     *
     * @param money the money to be subtracted from the pet's total money
     * @throws TamagotchiException if the pet does not have enough money
     */
    void spendMoney(float money) throws TamagotchiException;
}

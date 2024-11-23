package io.tamagotchi.pet;

import io.tamagotchi.food.Food;

/**
 * Represents a type of pet called Gradle in the Tamagotchi game.
 * This pet gains more health points (HP) from food.
 */
public class Gradle extends Pet {

    /**
     * Constructs a Gradle pet with default values.
     * The name is set to "Gradly", the image URL is set to "gradle.gif",
     * and the description is set to "Gains more HP from food."
     */
    public Gradle() {
        super();
        this.name = "Gradly";
        this.imageUrl = "gradle.gif";
        this.description = "Gains more HP from food.";
    }

    /**
     * Increases the health of the pet by the health value of the specified food,
     * with an additional bonus of 10 HP.
     *
     * @param food the food to be eaten by the pet
     */
    @Override
    public void eat(Food food) {
        super.eat(food.getHealth() + 10);
    }

    /**
     * Increases the health of the pet by the specified health value,
     * with an additional bonus of 10 HP.
     *
     * @param health the health value to be added to the pet's health
     */
    @Override
    public void eat(float health) {
        super.eat(health + 10);
    }

    /**
     * Returns a greeting message specific to the Gradle pet.
     *
     * @return a greeting message specific to the Gradle pet
     */
    @Override
    public String sayHello() {
        return "Let's build your knowledge together, one task at a time!";
    }
}

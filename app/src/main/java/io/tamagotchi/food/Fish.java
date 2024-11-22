package io.tamagotchi.food;

/**
 * Represents a type of food called Fish in the Tamagotchi game.
 */
public class Fish extends Food {

    /**
     * Constructs a Fish object with a predefined price and health value.
     * The image URL is set to "fish.png".
     */
    public Fish() {
        super(3, 23);
        this.imageUrl = "fish.png";
    }

    /**
     * Returns a message specific to the Fish type of food.
     *
     * @return a message indicating the Fish is healthy and tasty
     */
    @Override
    public String message() {
        return "Healthy and tasty - yum!";
    }
}

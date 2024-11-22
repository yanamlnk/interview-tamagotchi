package io.tamagotchi.food;

/**
 * Represents a type of food called Burger in the Tamagotchi game.
 */
public class Burger extends Food {

    /**
     * Constructs a Burger object with a predefined price and health value.
     * The image URL is set to "burger.png".
     */
    public Burger() {
        super(5, 35);
        this.imageUrl = "burger.png";
    }

    /**
     * Returns a message specific to the Burger type of food.
     *
     * @return a message indicating the Burger is enjoyable but should be eaten in moderation
     */
    @Override
    public String message() {
        return "I love burgers! Just don't eat too many of them!";
    }
}

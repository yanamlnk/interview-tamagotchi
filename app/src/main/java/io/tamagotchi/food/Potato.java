package io.tamagotchi.food;

/**
 * Represents a type of food called Potato in the Tamagotchi game.
 */
public class Potato extends Food {

    /**
     * Constructs a Potato object with a predefined price and health value.
     * The image URL is set to "potato.png".
     */
    public Potato() {
        super(1, 10);
        this.imageUrl = "potato.png";
    }

    /**
     * Returns a message specific to the Potato type of food.
     *
     * @return a message indicating the Potato is not very tasty, but it does the job.
     */
    @Override
    String message() {
        return "Potato is not very tasty, but it does the job!";
    }
}

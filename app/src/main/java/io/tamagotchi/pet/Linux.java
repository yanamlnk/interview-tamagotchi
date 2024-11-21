package io.tamagotchi.pet;

/**
 * Represents a type of pet called Linux in the Tamagotchi game.
 * This pet gets hungry slower.
 */
public class Linux extends Pet {

    /**
     * Constructs a Linux pet with default values.
     * The name is set to "Nux", the image URL is set to "linux.gif",
     * and the description is set to "Gets hungry slower."
     */
    public Linux() {
        super();
        this.name = "Nux";
        this.imageUrl = "linux.gif";
        this.description = "Gets hungry slower.";
    }

    /**
     * Decreases the health of the pet by the specified health value,
     * but at a slower rate compared to other pets.
     *
     * @param health the health value to be subtracted from the pet's health
     */
    @Override
    public void looseHealth(float health) {
        super.looseHealth(health - 10);
    }

    /**
     * Returns a greeting message specific to the Linux pet.
     *
     * @return a greeting message specific to the Linux pet
     */
    @Override
    public String sayHello() {
        return "Permission granted to execute your potential! Ready to learn?";
    }
}

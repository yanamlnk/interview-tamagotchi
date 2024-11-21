package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;

/**
 * Factory class for creating instances of different types of pets in the Tamagotchi game.
 */
public class PetFactory {

    /**
     * Creates a pet instance based on the given name.
     *
     * @param name the name of the pet type to create
     * @return a new instance of the specified pet type
     * @throws TamagotchiException if the pet type is unknown
     */
    public static Pet create(String name) throws TamagotchiException {
        return switch (name) {
            case "gradle" -> new Gradle();
            case "docker" -> new Docker();
            case "linux" -> new Linux();
            default -> throw new TamagotchiException("Unknown pet: " + name);
        };
    }
}

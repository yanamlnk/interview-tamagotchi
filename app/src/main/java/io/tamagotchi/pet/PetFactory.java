package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;

public class PetFactory {
    public static Pet create(String name) throws TamagotchiException {
        return switch (name) {
            case "gradle" -> new Gradle();
            case "docker" -> new Docker();
            case "linux" -> new Linux();
            default -> throw new TamagotchiException("Unknown pet: " + name);
        };
    }
}

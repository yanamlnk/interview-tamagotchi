package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetFactoryTests {
    @Test
    void testCreateValidPets() throws TamagotchiException {
        Pet gradle = PetFactory.create("gradle");
        assertInstanceOf(Gradle.class, gradle, "Gradle instance should be created.");
        assertEquals("Gradly", gradle.getName());

        Pet docker = PetFactory.create("docker");
        assertInstanceOf(Docker.class, docker, "Docker instance should be created.");
        assertEquals("Docky", docker.getName());

        Pet linux = PetFactory.create("linux");
        assertInstanceOf(Linux.class, linux, "Linux instance should be created.");
        assertEquals("Nux", linux.getName());
    }

    @Test
    void testCreateInvalidPet() {
        assertThrows(TamagotchiException.class, () -> PetFactory.create("unknown"), "Should throw exception for unknown pet.");
        try {
            PetFactory.create("unknown");
        } catch (TamagotchiException e) {
            assertEquals("Unknown pet: unknown", e.getMessage());
        }
    }
}

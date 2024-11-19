package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    @Test
    void testHealthManagement() {
        Pet linux = new Linux();
        Pet docker = new Docker();
        Pet gradle = new Gradle();

        linux.looseHealth(50);
        docker.looseHealth(50);
        gradle.looseHealth(50);
        assertEquals(60, linux.getHealth(), "Health should decrease with adjustment for Linux.");
        assertEquals(50, docker.getHealth(), "Health should decrease with no adjustment for Docker.");
        assertEquals(50, gradle.getHealth(), "Health should decrease with no adjustment for Gradle.");

        linux.eat(20);
        docker.eat(20);
        gradle.eat(20);
        assertEquals(80, linux.getHealth(), "Health should increase after eating with no adjustment for Linux.");
        assertEquals(70, docker.getHealth(), "Health should increase after eating with no adjustment for Docker.");
        assertEquals(80, gradle.getHealth(), "Health should increase after eating with no adjustment for Gradle.");

        linux.eat(30);
        assertEquals(100, linux.getHealth(), "Health should not exceed maximum of 100.");

        linux.looseHealth(100);
        assertFalse(linux.isDead(), "Linux should not be dead after health decrease 100 because of his superpower.");

        gradle.looseHealth(100);
        assertTrue(gradle.isDead(), "Pet should be dead after reaching health 0");

        docker.looseHealth(500);
        assertTrue(docker.isDead(), "Pet should be dead after health reaches 0.");
        assertEquals(0, docker.getHealth(), "Health should not go below 0.");
    }

    @Test
    void testXpGain() {
        Pet docker = new Docker();
        Pet linux = new Linux();
        Pet gradle = new Gradle();

        docker.gainXp(50);
        linux.gainXp(50);
        gradle.gainXp(50);

        assertEquals(60, docker.getTotalXp(), "XP should increase by the correct amount with adjustment for Docker.");
        assertEquals(50, linux.getTotalXp(), "XP should increase by the correct amount with no adjustment for Linux.");
        assertEquals(50, gradle.getTotalXp(), "XP should increase by the correct amount with no adjustment for Gradle.");
    }

    @Test
    void testMoneyManagement() {
        Pet pet = new Gradle();
        pet.gainMoney(100);
        assertEquals(100, pet.getMoney(), "Money should increase correctly.");

        assertThrows(TamagotchiException.class, () -> pet.spendMoney(200), "Should throw exception for insufficient funds.");
        try {
            pet.spendMoney(50);
        } catch (TamagotchiException e) {
            throw new RuntimeException(e);
        }
        assertEquals(50, pet.getMoney(), "Money should decrease correctly after spending.");
    }

    @Test
    void testChildClassBehaviors() {
        Pet linux = new Linux();
        assertEquals("Permission granted to execute your potential! Ready to learn?", linux.sayHello());

        Pet gradle = new Gradle();
        assertEquals("Let’s build your knowledge together, one task at a time!", gradle.sayHello());

        Pet docker = new Docker();
        assertEquals("Let’s containerize your skills and deploy success!", docker.sayHello());

        assertNotEquals(linux.getName(), gradle.getName(), "Linux and Gradle should have different names.");
        assertNotEquals(linux.getName(), docker.getName(), "Linux and Docker should have different names.");
        assertNotEquals(gradle.getName(), docker.getName(), "Gradle and Docker should have different names.");
    }

    @Test
    void testAvailabilityOfResources() {
        Pet linux = new Linux();
        Pet gradle = new Gradle();
        Pet docker = new Docker();

        assertNotNull(linux.getImageUrl(), "Linux should have an image URL.");
        assertNotNull(gradle.getImageUrl(), "Gradle should have an image URL.");
        assertNotNull(docker.getImageUrl(), "Docker should have an image URL.");

        assertNotNull(getClass().getResource("/" + linux.getImageUrl()), "Linux image with provided URL should exist.");
        assertNotNull(getClass().getResource("/" + gradle.getImageUrl()), "Gradle image with provided URL should exist.");
        assertNotNull(getClass().getResource("/" + docker.getImageUrl()), "Docker image with provided URL should exist.");

        assertNotNull(linux.getDescription(), "Linux should have a description.");
        assertNotNull(gradle.getDescription(), "Gradle should have a description.");
        assertNotNull(docker.getDescription(), "Docker should have a description.");
    }
}

package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.food.Burger;
import io.tamagotchi.food.Fish;
import io.tamagotchi.food.Food;
import io.tamagotchi.food.Potato;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTests {

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
    void testEatFood() {
        Pet linux = new Linux();
        Pet docker = new Docker();
        Pet gradle = new Gradle();

        Food burger = new Burger();
        Food fish = new Fish();
        Food potato = new Potato();

        linux.looseHealth(80);
        assertEquals(30, linux.getHealth(), "Health should be 30 after loosing 80 health with Linux superpower.");
        linux.eat(burger);
        assertEquals(65, linux.getHealth(), "Health should increase by 35 after eating burger.");
        linux.eat(fish);
        assertEquals(88, linux.getHealth(), "Health should increase by 23 after eating fish.");
        linux.eat(potato);
        assertEquals(98, linux.getHealth(), "Health should increase by 10 after eating potato.");

        docker.looseHealth(80);
        assertEquals(20, docker.getHealth(), "Health should be 20 after loosing 80 health.");
        docker.eat(burger);
        assertEquals(55, docker.getHealth(), "Health should increase by 35 after eating burger.");
        docker.eat(fish);
        assertEquals(78, docker.getHealth(), "Health should increase by 23 after eating fish.");
        docker.eat(potato);
        assertEquals(88, docker.getHealth(), "Health should increase by 10 after eating potato.");

        gradle.looseHealth(90);
        assertEquals(10, gradle.getHealth(), "Health should be 10 after loosing 90 health.");
        gradle.eat(burger);
        assertEquals(55, gradle.getHealth(), "Health should increase by 45 after eating burger (with Gradle superpower).");
        gradle.eat(fish);
        assertEquals(88, gradle.getHealth(), "Health should increase by 33 after eating fish (with Gradle superpower).");
        gradle.looseHealth(10);
        assertEquals(78, gradle.getHealth(), "Health should decrease by 10 after loosing 10 health.");
        gradle.eat(potato);
        assertEquals(98, gradle.getHealth(), "Health should increase by 20 after eating potato (with Gradle superpower).");
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

    @Test
    void testLevelAndXpManagement() {
        Pet linux = new Linux();
        assertEquals(1, linux.getLevel(), "Initial level should be 1");
        assertFalse(linux.isWinner(), "Pet should not be a winner at level 1.");

        linux.gainXp(29);
        assertEquals(1, linux.getLevel(), "Level should still be 1 after gaining 29 XP.");
        assertEquals(29, linux.getTotalXp(), "Total XP should be 29 after gaining 29 XP.");
        assertEquals(29, linux.getCurrentXp(), "Current XP should be 29 after gaining 29 XP.");
        assertEquals(30, linux.getMaxXpForThisLevel(), "Maximum XP for level 1 should be 30.");

        linux.gainXp(1);
        assertEquals(2, linux.getLevel(), "Level should be 2 with total of 30 XP.");
        assertEquals(30, linux.getTotalXp(), "Total XP should be 30 after gaining 1 XP in plus to 29 XP.");
        assertEquals(0, linux.getCurrentXp(), "Current XP should be 0 just after leveling up.");
        assertEquals(40, linux.getMaxXpForThisLevel(), "Maximum XP for level 2 should be 40.");
        assertTrue(linux.getCurrentXp() < linux.getMaxXpForThisLevel(), "Current XP should reset correctly when leveling up.");

        linux.gainXp(50);
        assertEquals(3, linux.getLevel(), "Level should be 3 with total of 80 XP.");
        assertEquals(80, linux.getTotalXp(), "Total XP should be 80 after gaining 50 XP in plus to 30 XP.");
        assertEquals(10, linux.getCurrentXp(), "Current XP should be 10 after leveling up.");
        assertEquals(50, linux.getMaxXpForThisLevel(), "Maximum XP for level 3 should be 50.");
        assertTrue(linux.getCurrentXp() < linux.getMaxXpForThisLevel(), "Current XP should reset correctly when leveling up.");

        linux.gainXp(800);
        assertTrue(linux.isWinner(), "Pet should be a winner after reaching level 10.");
        assertEquals(10, linux.getLevel(), "Level should be 10 with total of 880 XP.");
        assertEquals(880, linux.getTotalXp(), "Total XP should be 880 after gaining 800 XP in plus to 80 XP.");
        assertEquals(0, linux.getCurrentXp(), "Current XP should be 0 just after leveling up.");
        assertEquals(0, linux.getMaxXpForThisLevel(), "Maximum XP for level 10 should be 0.");
    }
}

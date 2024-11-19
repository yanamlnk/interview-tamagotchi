package io.tamagotchi.food;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTests {
    @Test
    void testFish() {
        Food fish = new Fish();
        assertEquals(3, fish.getPrice());
        assertEquals(23, fish.getHealth());
        assertEquals("fish.png", fish.getImageUrl());
        assertEquals("Healthy and tasty - yum!", fish.message());
    }

    @Test
    void testPotato() {
        Food potato = new Potato();
        assertEquals(1, potato.getPrice());
        assertEquals(10, potato.getHealth());
        assertEquals("potato.png", potato.getImageUrl());
        assertEquals("Potato is not very tasty, but it does the job!", potato.message());
    }

    @Test
    void testBurger() {
        Food burger = new Burger();
        assertEquals(5, burger.getPrice());
        assertEquals(35, burger.getHealth());
        assertEquals("burger.png", burger.getImageUrl());
        assertEquals("I love burgers! Just don't eat too many of them!", burger.message());
    }

    @Test
    void testImageResources() {
        Food burger = new Burger();
        Food fish = new Fish();
        Food potato = new Potato();

        assertNotNull(getClass().getResource("/" + burger.getImageUrl()));
        assertNotNull(getClass().getResource("/" + fish.getImageUrl()));
        assertNotNull(getClass().getResource("/" + potato.getImageUrl()));
    }
}

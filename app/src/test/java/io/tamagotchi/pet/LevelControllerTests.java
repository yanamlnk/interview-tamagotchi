package io.tamagotchi.pet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LevelControllerTests {

    @Test
    @DisplayName("Tests for getting level based on XP using LevelController.")
    void testGetLevel() {
        assertEquals(1, LevelController.getLevel(10), "Level 1 should match.");
        assertEquals(2, LevelController.getLevel(50), "Level 2 should match.");
        assertEquals(5, LevelController.getLevel(200), "Level 5 should match.");
        assertEquals(10, LevelController.getLevel(700), "Level 10 should match.");
    }

    @Test
    @DisplayName("Tests for getting max XP for given level using LevelController.")
    void testGetXpForThisLevel() {
        assertEquals(30, LevelController.getXpForThisLevel(1), "XP for Level 1 should be 30.");
        assertEquals(250, LevelController.getXpForThisLevel(5), "XP for Level 5 should be 250.");
        assertEquals(670, LevelController.getXpForThisLevel(9), "XP for Level 9 should be 670.");
        assertEquals(670, LevelController.getXpForThisLevel(10), "XP for Level 10 should be 670.");
    }
}


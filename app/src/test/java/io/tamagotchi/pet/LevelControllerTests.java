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
        assertEquals(4, LevelController.getLevel(170), "Level should be 4 for xp between 120 and 180");
        assertEquals(6, LevelController.getLevel(300), "Level should be 6 for xp between 250 and 340");
        assertEquals(7, LevelController.getLevel(439), "Level should be 7 for xp between 340 and 440");
        assertEquals(8, LevelController.getLevel(440), "Level should be 8 for xp between 440 and 550");
        assertEquals(9, LevelController.getLevel(600), "Level should be 9 for xp between 550 and 670");
        assertEquals(5, LevelController.getLevel(200), "Level 5 should match.");
        assertEquals(10, LevelController.getLevel(700), "Level 10 should match.");
    }

    @Test
    @DisplayName("Tests for getting max XP for given level using LevelController.")
    void testGetXpForThisLevel() {
        assertEquals(30, LevelController.getXpForThisLevel(1), "XP for Level 1 should be 30.");
        assertEquals(180, LevelController.getXpForThisLevel(4), "XP for Level 4 should be 180.");
        assertEquals(340, LevelController.getXpForThisLevel(6), "XP for Level 6 should be 340.");
        assertEquals(440, LevelController.getXpForThisLevel(7), "XP for Level 7 should be 440.");
        assertEquals(550, LevelController.getXpForThisLevel(8), "XP for Level 8 should be 550.");
        assertEquals(250, LevelController.getXpForThisLevel(5), "XP for Level 5 should be 250.");
        assertEquals(670, LevelController.getXpForThisLevel(9), "XP for Level 9 should be 670.");
        assertEquals(670, LevelController.getXpForThisLevel(10), "XP for Level 10 should be 670.");
    }
}


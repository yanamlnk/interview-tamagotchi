package io.tamagotchi.pet;

/**
 * Utility class for managing levels and experience points (XP) in the Tamagotchi game.
 */
public class LevelController {

    /**
     * Returns the level corresponding to the given experience points (XP).
     *
     * @param xp the experience points
     * @return the level corresponding to the given experience points
     */
    static int getLevel(float xp) {
        if (xp < 30) {
            return 1;
        } else if (xp < 70) {
            return 2;
        } else if (xp < 120) {
            return 3;
        } else if (xp < 180) {
            return 4;
        } else if (xp < 250) {
            return 5;
        } else if (xp < 340) {
            return 6;
        } else if (xp < 440) {
            return 7;
        } else if (xp < 550) {
            return 8;
        } else if (xp < 670) {
            return 9;
        } else {
            return 10;
        }
    }

    /**
     * Returns the maximum experience points (XP) of the given level.
     *
     * @param level the level
     * @return the experience points required for the given level
     */
    static float getXpForThisLevel(int level) {
        return switch (level) {
            case 1 -> 30;
            case 2 -> 70;
            case 3 -> 120;
            case 4 -> 180;
            case 5 -> 250;
            case 6 -> 340;
            case 7 -> 440;
            case 8 -> 550;
            case 9, 10 -> 670;
            default -> 0;
        };
    }
}

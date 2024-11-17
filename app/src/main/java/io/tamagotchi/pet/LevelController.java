package io.tamagotchi.pet;

public class LevelController {
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
            case 9 -> 670;
            default -> 0;
        };
    }
}

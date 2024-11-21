package io.tamagotchi.food;

public class Burger extends Food {
    public Burger() {
        super(5, 35);
        this.imageUrl = "burger.png";
    }

    @Override
    String message() {
        return "I love burgers! Just don't eat too many of them!";
    }
}

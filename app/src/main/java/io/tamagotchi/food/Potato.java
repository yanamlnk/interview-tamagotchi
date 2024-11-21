package io.tamagotchi.food;

public class Potato extends Food {
    public Potato() {
        super(1, 10);
        this.imageUrl = "potato.png";
    }

    @Override
    String message() {
        return "Potato is not very tasty, but it does the job!";
    }
}

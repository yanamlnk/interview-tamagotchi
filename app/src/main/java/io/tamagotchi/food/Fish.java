package io.tamagotchi.food;

public class Fish extends Food{
    public Fish() {
        super(3, 23);
        this.imageUrl = "fish.png";
    }

    @Override
    String message() {
        return "Healthy and tasty - yum!";
    }
}

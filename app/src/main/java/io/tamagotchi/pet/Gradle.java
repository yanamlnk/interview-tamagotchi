package io.tamagotchi.pet;

import io.tamagotchi.food.Food;

public class Gradle extends Pet{
    public Gradle() {
        super();
        this.name = "Gradly";
        this.imageUrl = "gradle.gif";
        this.description = "Gains more HP from food.";
    }

    @Override
    public void eat(Food food) {
        super.eat(food.getHealth() + 10);
    }

    @Override
    public void eat(float health) {
        super.eat(health + 10);
    }

    @Override
    public String sayHello() {
        return "Let’s build your knowledge together, one task at a time!";
    }
}

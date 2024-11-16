package io.tamagotchi.pet;

import io.tamagotchi.food.Food;

public class Gradle extends Pet{
    public Gradle() {
        super();
        this.name = "Gradly";
        this.imageUrl = "gradle.png";
        this.description = "Gains more HP from food.";
    }

    @Override
    public void eat(Food food) {
        super.eat(food.getHealth() + 10);
    }
}

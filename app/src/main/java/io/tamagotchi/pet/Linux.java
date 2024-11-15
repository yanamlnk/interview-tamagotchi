package io.tamagotchi.pet;

public class Linux extends Pet {
    public Linux() {
        super();
        this.name = "Nux";
        this.imageUrl = "linux.png";
    }

    @Override
    public void looseHealth(float health) {
        super.looseHealth(health - 10);
    }
}

package io.tamagotchi.pet;

public class Docker extends Pet {

    public Docker() {
        super();
        this.name = "Docky";
        this.imageUrl = "docker.png";
    }

    @Override
    public void gainXp(float xp) {
        super.gainXp(xp + 10);
    }
}

package io.tamagotchi.pet;

public class Docker extends Pet {

    public Docker() {
        super();
        this.name = "Docky";
        this.imageUrl = "docker.gif";
        this.description = "Gains more XP from games.";
    }

    @Override
    public void gainXp(float xp) {
        super.gainXp(xp + 10);
    }

    @Override
    public String sayHello() {
        return "Let’s containerize your skills and deploy success!";
    }
}

package io.tamagotchi.pet;

/**
 * Represents a type of pet called Docker in the Tamagotchi game.
 * This pet gains more experience points (XP) from games.
 */
public class Docker extends Pet {

    /**
     * Constructs a Docker pet with default values.
     * The name is set to "Docky", the image URL is set to "docker.gif",
     * and the description is set to "Gains more XP from games."
     */
    public Docker() {
        super();
        this.name = "Docky";
        this.imageUrl = "docker.gif";
        this.description = "Gains more XP from games.";
    }

    /**
     * Increases the experience points of the pet by the specified value,
     * with an additional bonus of 10 XP.
     *
     * @param xp the experience points to be added to the pet's experience
     */
    @Override
    public void gainXp(float xp) {
        super.gainXp(xp + 10);
    }

    /**
     * Returns a greeting message specific to the Docker pet.
     *
     * @return a greeting message specific to the Docker pet
     */
    @Override
    public String sayHello() {
        return "Let’s containerize your skills and deploy success!";
    }
}

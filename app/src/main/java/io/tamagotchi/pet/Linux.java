package io.tamagotchi.pet;

public class Linux extends Pet {
    public Linux() {
        super();
        this.name = "Nux";
        this.imageUrl = "linux.gif";
        this.description = "Gets hungry slower.";
    }

    @Override
    public void looseHealth(float health) {
        super.looseHealth(health - 10);
    }

    @Override
    public String sayHello() {
        return "Permission granted to execute your potential! Ready to learn?";
    }
}

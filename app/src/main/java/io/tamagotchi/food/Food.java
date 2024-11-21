package io.tamagotchi.food;

public abstract class Food {
    private int price;
    private int health;

    protected String imageUrl;

    public Food(int price, int health) {
        this.price = price;
        this.health = health;
    }

    abstract String message();

    public int getPrice() {
        return price;
    }

    public int getHealth() {
        return health;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

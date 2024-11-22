package io.tamagotchi.food;

/**
 * Abstract class representing a type of food in the Tamagotchi game.
 */
public abstract class Food {
    /**
     * The price of the food.
     */
    private int price;

    /**
     * The health value of the food.
     */
    private int health;

    /**
     * The URL of the image representing the food.
     */
    protected String imageUrl;

    /**
     * Constructs a Food object with the specified price and health value.
     *
     * @param price  the price of the food
     * @param health the health value of the food
     */
    public Food(int price, int health) {
        this.price = price;
        this.health = health;
    }

    /**
     * Returns a message specific to the type of food.
     *
     * @return a message specific to the type of food
     */
    public abstract String message();

    /**
     * Returns the price of the food.
     *
     * @return the price of the food
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the health value of the food.
     *
     * @return the health value of the food
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the URL of the image representing the food.
     *
     * @return the URL of the image representing the food
     */
    public String getImageUrl() {
        return imageUrl;
    }
}

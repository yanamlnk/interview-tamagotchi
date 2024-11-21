package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.food.Food;

/**
 * Abstract class representing a pet in the Tamagotchi game.
 */
public abstract class Pet implements Tamagotchi {
    /**
     * The health of the pet.
     */
    private float health;

    /**
     * The total experience points of the pet.
     */
    private float totalXp;

    /**
     * The current experience points of the pet on the current level.
     */
    private float currentXp;

    /**
     * The maximum experience points required for the current level.
     */
    private float maxXpForThisLevel;

    /**
     * The amount of money the pet has.
     */
    private float money;

    /**
     * The level of the pet.
     */
    private int level;

    /**
     * Indicates whether the pet is a winner.
     */
    private boolean winner;

    /**
     * Indicates whether the pet is dead.
     */
    private boolean dead;

    /**
     * The name of the pet.
     */
    protected String name;

    /**
     * The URL of the image representing the pet.
     */
    protected String imageUrl;

    /**
     * The description of the pet.
     */
    protected String description;

    /**
     * Constructs a Pet object with default values.
     */
    public Pet() {
        this.health = 100;
        this.totalXp = 0;
        this.currentXp = 0;
        this.maxXpForThisLevel = LevelController.getXpForThisLevel(1);
        this.money = 0;
        this.level = 1;
        this.winner = false;
    }

    /**
     * Returns a greeting message specific to the type of pet.
     *
     * @return a greeting message specific to the type of pet
     */
    public abstract String sayHello();

    /**
     * Returns the health of the pet.
     *
     * @return the health of the pet
     */
    public float getHealth() {
        return health;
    }

    /**
     * Returns the total experience points of the pet.
     *
     * @return the total experience points of the pet
     */
    public float getTotalXp() {
        return totalXp;
    }

    /**
     * Returns the current experience points of the pet in the current level.
     *
     * @return the current experience points of the pet
     */
    public float getCurrentXp() {
        return currentXp;
    }

    /**
     * Returns the maximum experience points required for the current level.
     *
     * @return the maximum experience points required for the current level
     */
    public float getMaxXpForThisLevel() {
        return maxXpForThisLevel;
    }

    /**
     * Returns the amount of money the pet has.
     *
     * @return the amount of money the pet has
     */
    public float getMoney() {
        return money;
    }

    /**
     * Returns the level of the pet and immediately sets the level.
     *
     * @return the level of the pet
     */
    public int getLevel() {
        this.setLevel();
        return level;
    }

    /**
     * Returns whether the pet is a winner.
     *
     * @return true if the pet is a winner, false otherwise
     */
    public boolean isWinner() {
        return winner;
    }

    /**
     * Returns whether the pet is dead.
     *
     * @return true if the pet is dead, false otherwise
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Returns the name of the pet.
     *
     * @return the name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the URL of the image representing the pet.
     *
     * @return the URL of the image representing the pet
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Returns the description of the pet.
     *
     * @return the description of the pet
     */
    public String getDescription() {
        return description;
    }

    /**
     * Increases the health of the pet by the health value of the specified food. Total health cannot exceed 100.
     *
     * @param food the food to be eaten by the pet
     */
    @Override
    public void eat(Food food) {
        this.health = Math.min(100, this.health + food.getHealth());
    }

    /**
     * Increases the health of the pet by the specified health value. Total health cannot exceed 100.
     *
     * @param health the health value to be added to the pet's health
     */
    public void eat(float health) {
        this.health = Math.min(100, this.health + health);
    }

    /**
     * Decreases the health of the pet by the specified health value. Total health cannot be less than 0.
     *
     * @param health the health value to be subtracted from the pet's health
     */
    @Override
    public void looseHealth(float health) {
        this.health = Math.max(0, this.health - health);
        if (this.health == 0) {
            this.dead = true;
        }
    }

    /**
     * Increases the amount of money the pet has by the specified value.
     *
     * @param money the amount of money to be added to the pet's money
     */
    @Override
    public void gainMoney(float money) {
        this.money += money;
    }

    /**
     * Sets the level of the pet based on its total experience points.
     */
    @Override
    public void setLevel() {
        this.level = LevelController.getLevel(this.totalXp);
        if (this.level == 10) {
            this.winner = true;
        }
    }

    /**
     * Increases the experience points of the pet by the specified value,
     * as well as sets the value of current experience, level and maximum
     * amount of experience needed for current value.
     *
     * @param xp the experience points to be added to the pet's experience
     */
    @Override
    public void gainXp(float xp) {
        this.totalXp += xp;
        this.currentXp += xp;
        this.setLevel();
        if (currentXp >= maxXpForThisLevel) {
            if (this.level == 10) {
                currentXp = 0;
            } else {
                currentXp = totalXp - LevelController.getXpForThisLevel(this.level - 1);
            }
        }
        maxXpForThisLevel = LevelController.getXpForThisLevel(this.level) - LevelController.getXpForThisLevel(this.level - 1);
    }

    /**
     * Decreases the amount of money the pet has by the specified value.
     *
     * @param money the amount of money to be subtracted from the pet's money
     * @throws TamagotchiException if the pet does not have enough money
     */
    @Override
    public void spendMoney(float money) throws TamagotchiException {
        if (money > this.money) {
            throw new TamagotchiException("Not enough money");
        }
        this.money -= money;
    }
}

package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.food.Food;

public abstract class Pet implements Tamagotchi {
    private float health;
    private float totalXp;
    private float currentXp;
    private float maxXpForThisLevel;
    private float money;
    private int level;
    private boolean winner;
    private boolean dead;

    protected String name;
    protected String imageUrl;
    protected String description;

    public Pet() {
        this.health = 100;
        this.totalXp = 0;
        this.currentXp = 0;
        this.maxXpForThisLevel = LevelController.getXpForThisLevel(1);
        this.money = 0;
        this.level = 1;
        this.winner = false;
    }

    public abstract String sayHello();

    public float getHealth() {
        return health;
    }

    public float getTotalXp() {
        return totalXp;
    }

    public float getCurrentXp() {
        return currentXp;
    }

    public float getMaxXpForThisLevel() {
        return maxXpForThisLevel;
    }

    public float getMoney() {
        return money;
    }

    public int getLevel() {
        this.setLevel();
        return level;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isDead() {
        return dead;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void eat(Food food) {
        this.health = Math.min(100, this.health + food.getHealth());
    }

    public void eat(float health) {
        this.health = Math.min(100, this.health + health);
    }

    @Override
    public void looseHealth(float health) {
        this.health = Math.max(0, this.health - health);
        if (this.health == 0) {
            this.dead = true;
        }
    }

    @Override
    public void gainMoney(float money) {
        this.money += money;
    }

    @Override
    public void setLevel() {
        this.level = LevelController.getLevel(this.totalXp);
        if (this.level == 10) {
            this.winner = true;
        }
    }

    @Override
    public void gainXp(float xp) {
        this.totalXp += xp;
        this.currentXp += xp;
        this.setLevel();
        maxXpForThisLevel = LevelController.getXpForThisLevel(this.level) - LevelController.getXpForThisLevel(this.level - 1);
        if (currentXp >= maxXpForThisLevel) {
            currentXp = totalXp - LevelController.getXpForThisLevel(this.level - 1);
        }
    }

    @Override
    public void spendMoney(float money) throws TamagotchiException {
        if (money > this.money) {
            throw new TamagotchiException("Not enough money");
        }
        this.money -= money;
    }
}

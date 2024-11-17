package io.tamagotchi.pet;

import io.tamagotchi.TamagotchiException;
import io.tamagotchi.food.Food;

public interface Tamagotchi {
    void eat(Food food);

    void looseHealth(float health);

    void gainXp(float xp);

    void gainMoney(float money);

    void setLevel();

    void spendMoney(float money) throws TamagotchiException;
    //can also add void play(Game game);
}

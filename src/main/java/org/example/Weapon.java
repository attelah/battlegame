package org.example;

import java.util.Random;

public class Weapon {
    private String name;
    private int damage;
    Random rnd = new Random();

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = rnd.nextInt(8) + damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

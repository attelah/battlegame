package org.example;

import java.io.Serializable;
import java.util.Random;

public class Weapon implements Serializable {
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

    public int getDamage() {
        return damage;
    }

}

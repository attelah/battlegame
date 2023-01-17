package org.example;

public class GameCharacter {
    private String name;
    private int hitPoints;

    public GameCharacter(String name, int hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
    }

    public int takeDamage(int damage) {
        this.hitPoints -= damage;
        if (this.hitPoints < 0) this.hitPoints = 0;
        return this.hitPoints;
    }

    public String getName() {
        return name;
    }

    public double getHitPoints() { return hitPoints; }

}

package org.example;

abstract class GameCharacter {
    public String name;
    public int hitPoints;
    private Weapon equippedWeapon;
    double dexterity;
    GameCharacter defender;
    int damageAmount;


    public GameCharacter(String name, int hitPoints, Weapon equippedWeapon) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.equippedWeapon = equippedWeapon;
        this.dexterity = dexterity;
    }

    public int takeDamage(int damage) {
        this.hitPoints -= damage;
        if (this.hitPoints < 0) this.hitPoints = 0;
        return damage;
    }

    public double attack(GameCharacter defender) {
        this.defender = defender;
        return damageAmount = defender.takeDamage((int) (getEquippedWeapon().getDamage()*getDexterity()));
    }

    protected GameCharacter() {
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public String getName() {
        return name;
    }

    public double getHitPoints() { return hitPoints; }

    public double getDexterity() {
        return dexterity;
    }

    public void setDexterity(double dexterity) {
        this.dexterity = dexterity;
    }
}

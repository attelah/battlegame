package org.example;

abstract class GameCharacter {
    public String name;
    public int hitPoints;
    private String equippedWeapon = "Fists";
    double dexterity;
    GameCharacter defender;
    double damageRange;
    double getWeaponDamage;

    public GameCharacter(String name, int hitPoints, String equippedWeapon) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.equippedWeapon = equippedWeapon;
        this.dexterity = dexterity;
    }

    public int takeDamage(int damage) {
        this.hitPoints -= damage;
        if (this.hitPoints < 0) this.hitPoints = 0;
        return this.hitPoints;
    }

    public double attack(GameCharacter defender) {
        this.defender = defender;
        damageRange = defender.takeDamage(getEquippedWeapon().getWeaponDamage());
        return getDamage(damageRange);
    }

    protected GameCharacter() {
    }

    public String getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(String equippedWeapon) {
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

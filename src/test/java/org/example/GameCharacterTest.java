package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    void takeDamage() {
        Weapon weapon = new Weapon("Sword", 10);
        GameCharacter g = new Player("foo", 100, 0.8, weapon);
        g.takeDamage(50);
        assertEquals(50, g.getHitPoints());
    }

    @Test
    void getName() {
        Weapon weapon = new Weapon("Sword", 10);
        GameCharacter g = new Npc("foo", 100, 0.5, weapon);
        assertEquals("foo", g.getName());
    }

    @Test
    void npcCreate() {
        Weapon weapon = new Weapon("Sword", 10);
        GameCharacter npc = new Npc("foo", 100, 0.5, weapon);
        assertEquals("foo", npc.getName());
        assertEquals(100.0, npc.getHitPoints());
        assertEquals(0.5, npc.getDexterity());
        assertEquals("Sword", npc.getEquippedWeapon().getName());
    }

    @Test
    void npcAttack() {
        Weapon weapon = new Weapon("Sword", 10);
        GameCharacter npc = new Npc("foo", 100, 0.5, weapon);
        GameCharacter player = new Player("foo", 100, 0.8, weapon);
        npc.attack(player, 0);
        assertEquals(100, npc.getHitPoints());
        assertNotEquals(100, player.getHitPoints());
    }

    @Test
    void playerCreate() {
        Weapon weapon = new Weapon("Sword", 10);
        GameCharacter player = new Player("foo", 100, 0.8, weapon);
        assertEquals("foo", player.getName());
        assertEquals(100, player.getHitPoints());
        assertEquals(0.8, player.getDexterity());
        assertEquals("Sword", player.getEquippedWeapon().getName());
    }

    @Test
    void playerAttack() {
        Weapon weapon = new Weapon("Sword", 10);
        GameCharacter npc = new Npc("foo", 100, 0.5, weapon);
        GameCharacter player = new Player("foo", 100, 0.8, weapon);
        player.attack(npc, 0);
        assertEquals(100, player.getHitPoints());
        assertNotEquals(100, npc.getHitPoints());
    }

    @Test
    void saveGameTest() {
        Weapon weapon = new Weapon("Sword", 10);
        GameCharacter player = new Player("foo", 100, 0.8, weapon);
        FileUtils.saveObject(player, "testGame.save");
        GameCharacter savedPlayer = (Player) FileUtils.loadObject("testGame.save");
        assertEquals(player.getName(), savedPlayer.getName());
        assertEquals(player.getInventory(), savedPlayer.getInventory());
    }

}
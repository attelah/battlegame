package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    void takeDamage() {
        GameCharacter g = new Player("foo", 100, 0.8);
        g.takeDamage(50);
        assertEquals(50, g.getHitPoints());
    }

    @Test
    void getName() {
        GameCharacter g = new Npc("foo", 100, 0.5);
        assertEquals("foo", g.getName());
    }
}
package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    void takeDamage() {
        GameCharacter g = new GameCharacter("foo", 100);
        g.takeDamage(50);
        assertEquals(50, g.getHitPoints());
    }

    @Test
    void getName() {
        GameCharacter g = new GameCharacter("foo", 100);
        assertEquals("foo", g.getName());
    }
}
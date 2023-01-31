package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    void getName() {
        Weapon weapon = new Weapon("foo", 10);
        assertEquals("foo", weapon.getName());
    }
}
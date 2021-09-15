package com.game.model.materials;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class DefensesTest {
    Map<String, String> testValues;
    Defenses defenses = new Defenses();

    @Before
    public void setup() {
        testValues = new HashMap<>();
        testValues.put("Crypsis", "Your coloring offers you natural camouflage from predators.");
        testValues.put("Stinging Hairs", "You have grown tiny hairs with irritating poison to injure and discourage predators.");
        testValues.put("Stink Attack", "You can now eject a violently bad smell that scares away predators.");
        testValues.put("Noxious Spit","You have evolved the ability to eject noxious poison from your face. The loud clicking noise it makes is a further deterrent.");
        testValues.put("Click Defense", "Your organs can make clicking sounds that scare off predators and block bat sonar.");
        testValues.put("Ant Alliance", "You secrete a sweet liquid that ants find irresistible. They will fight to the death to protect you and your sweet, sweet nectar.");
        testValues.put("Acid Attack", "Spit acid on any target, you totally metal butterfly!");
    }

    @Test
    public void getNameIsCorrectForValidLevel() {
        assertEquals("Crypsis", defenses.getName(1));
        assertEquals("Acid Attack", defenses.getName(7));
    }

    @Test
    public void getNameIsEmptyStringForInvalidLevel() {
        assertEquals("", defenses.getName(0));
        assertEquals("", defenses.getName(-1));
        assertEquals("", defenses.getName(8));
        assertEquals("", defenses.getName(100));
    }

    @Test
    public void getDescriptionIsCorrectForValidLevel() {
        assertEquals("You can now eject a violently bad smell that scares away predators.", defenses.getDescription(3));
        assertEquals("Spit acid on any target, you totally metal butterfly!", defenses.getDescription(7));
    }

    @Test
    public void getDescriptionIsEmptyForInvalidLevel() {
        assertEquals("", defenses.getDescription(0));
        assertEquals("", defenses.getDescription(-1));
        assertEquals("", defenses.getDescription(8));
    }

    @Test
    public void getDefensesIsValidForLevel1() {
        Map<String, String> defenseMap = defenses.getDefenses(1);
        assertEquals(1, defenseMap.size());
        assertTrue(defenseMap.containsKey("Crypsis"));
        assertEquals("Your coloring offers you natural camouflage from predators.", defenseMap.get("Crypsis"));
        assertFalse(defenseMap.containsKey("Stinging Hairs"));
    }

    @Test
    public void getDefensesIsValidForMultipleLevels() {
        Map<String, String> defenseMap = defenses.getDefenses(3);
        assertEquals(3, defenseMap.size());
        assertTrue(defenseMap.containsKey("Crypsis"));
        assertTrue(defenseMap.containsKey("Stinging Hairs"));
        assertTrue(defenseMap.containsKey("Stink Attack"));
        assertFalse(defenseMap.containsKey("Ant Alliance"));
    }

    @Test
    public void getDefensesReturnsNullForInvalidLevel() {
        assertNull(defenses.getDefenses(0));
        assertNull(defenses.getDefenses(-1));
    }

    @Test
    public void getDefensesReturnsCappedMapForInvalidLevelsOverMax() {
        assertEquals(7, defenses.getDefenses(8).size());
        assertEquals(7, defenses.getDefenses(100).size());
    }
}
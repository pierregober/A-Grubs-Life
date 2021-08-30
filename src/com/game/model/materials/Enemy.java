package com.game.model.materials;

public class Enemy {

    private String name;
    private int health;
    private int requiredLevelToFight;
    private boolean aggressive;
    private int strength;
    private boolean tamable;
    private String location;
    public Enemy(String name, int health, int strength, int requiredLevelToFight, boolean aggressive, boolean tamable, String location){
        this.name = name;
        this.health = health;
        this.requiredLevelToFight = requiredLevelToFight;
        this.aggressive = aggressive;
        this.strength = strength;
        this.tamable = tamable;
        this.location = location;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getRequiredLevelToFight() {
        return requiredLevelToFight;
    }

    public boolean isAggressive() {
        return aggressive;
    }

    public int getStrength() {
        return strength;
    }

    public boolean isTamable() {
        return tamable;
    }

    public String getLocation() {
        return location;
    }
}

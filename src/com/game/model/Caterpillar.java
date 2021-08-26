package com.game.model;


import org.jetbrains.annotations.NotNull;

class Caterpillar {
    private int health;
    private int experience;
    private int strength;
    private int maxHealth;
    private int level = 1;
    private int maxLevel;
    private int maxExperience;
    private boolean isButterfly;

    public  Caterpillar(int health, int experience, int strength){
        this.health = health;
        this.health = maxHealth;
        this.experience = experience;
        this.strength = strength;
    }

    public void eat(String object){
        if(object == "leaf")
            setHealth(maxHealth);
            setExperience(experience + 1);
    }
    public void levelUp(){
        if(experience == maxExperience - 1) {
            setExperience(0);
            setStrength(strength + 1);
            setLevel(level + 1);
            if(getLevel() == maxLevel){
                endStage();
            }
        }
    }

    public void fightGetHit(@NotNull Enemy enemy){
        int strengthAdvantage = 0;
        if(enemy.getStrength() > getStrength()){
            strengthAdvantage = 1;
            setHealth(health - enemy.getStrength() - strengthAdvantage);
        }
        else{
            setHealth(health - enemy.getStrength());
        };
    }

    public void fightHitBack(@NotNull Enemy enemy){
        int strengthAdvantage = 0;
        if(enemy.getStrength() < getStrength()){
            strengthAdvantage = 1;
            enemy.setHealth(enemy.getHealth() - strength - strengthAdvantage);
        }
        else{
            enemy.setHealth(enemy.getHealth() - strength);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

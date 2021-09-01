package com.game.model.materials;

public class Caterpillar {
    private int health;
    private int experience;
    private int strength ;
    private int maxHealth = 10;
    private int level = 3;
    private int maxLevel = 3;
    private int maxExperience = 1;
    private boolean isButterfly;
    private Location currentLocation;
    private boolean hidden;
    private String lastAction;

    public  Caterpillar(int health, int experience, int strength){
        this.health = health;
        this.experience = experience;
        this.strength = strength;
        this.hidden = false;
        this.lastAction = "";
    }
    public void setCurrentLocation(Location location){ //we should move this to the bottom
        this.currentLocation = location;
    }
    public Location getCurrentLocation(){
        return this.currentLocation;
    }
    public void eat(Leaf leaf){
        setHealth(getHealth() + 10);
        if( (getExperience() + leaf.getXp()) > maxExperience && level < 5) {
            setExperience((getExperience() + leaf.getXp()) % maxExperience); //level up and transfers remaining to experience
            levelUp(); //increases level / ends the stage once appropriate level
            }
        else{
            if(level < 5){
                setExperience(getExperience() + leaf.getXp() ); // no levelup by experience up
            }

        }
    }
    public void levelUp(){
        setStrength(strength + 50);
        setLevel(level + 1);
        if(getLevel() == maxLevel){
            isButterfly = true;
            }
    }

    public void healthRegenerator(int counter){
        if(counter % 2934342 == 0){
            setHealth(getHealth() + 1);
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }


    public int getMaxHealth() {
        return this.maxHealth;
    }
    public void setLastAction(String str){
        this.lastAction = str;
    }
    public String getLastAction(){
        return this.lastAction;
    }
}

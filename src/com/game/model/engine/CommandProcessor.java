package com.game.model.engine;


import com.game.model.materials.Caterpillar;
import com.game.model.materials.Enemy;
import com.game.model.materials.Location;

import java.util.*;

public class CommandProcessor {
    private Caterpillar caterpillar;
    private HashMap<String, Location> locations;
    private HashMap<String, Enemy> enemies;
    private Enemy enemy;  // shortcut for finding your enemy
    private boolean misfire;

    public CommandProcessor(Caterpillar caterpillar, HashMap<String,Location> locations, HashMap<String, Enemy> enemies){
        this.caterpillar = caterpillar;
        this.locations = locations;
        this.enemies = enemies;

    }

    public void executeCommand(ArrayList<String> strings) {
        if (strings.size() == 2 && strings.get(0) != null && strings.get(1) != null) {
            this.enemy = enemies.get(caterpillar.getCurrentLocation().getName().toLowerCase());
            String action = strings.get(0).toUpperCase(Locale.ROOT);
            String focus = strings.get(1).toUpperCase(Locale.ROOT);
            this.misfire = true;
            processCommand(action,focus); // passing in to either the combat system or command menu..
        } else {
           processTypo();
        }
    }


    private void processCommand(String action, String focus){

        if ( enemies.get(caterpillar.getCurrentLocation().getName().toLowerCase()).isInCombat()) {
            runCombatCheck(action,focus);
        } else {
            runProcessMenu(action,focus);
            if(misfire){
                processCantDoThatHere();
            }
        }
    }
    private void runCombatCheck(String action, String focus){
        if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("ATTACK")) {
            processAttack(focus);
        } if (focus.toUpperCase(Locale.ROOT).equalsIgnoreCase("RUN")) {
            processRun(focus);
        }
    }
    private void processCantDoThatHere(){
        caterpillar.setLastAction("You can't do that here.. We don't have that ");
    }
    //This method is where to put any new commands.. each of the cases links out to the corresponding logic method... this is essentially a directory for incoming eligible commands.
    private void runProcessMenu(String action, String focus){
        switch(action.toUpperCase(Locale.ROOT)){
            case "GO":
                processNavigation(focus.toLowerCase());
                break;
            case "EAT":
                processEating(focus);
                break;
            case "HIDE":
                processHide(focus);
                break;
            case "HELP":
                processAntAssistance(focus);
                break;
            case "LEAVE":
                processLeave(focus);
                break;
            case "ATTACK":
                processAttack(focus);
                break;
            case "RUN":
                processRun(focus);
                break;
        }
    }
    private void processTypo() {
        caterpillar.setLastAction("I can't process that, try again with a verb/noun combo of relevant game objects.");
    }

    private void processAttack( String focus) {

        enemy.setInCombat(true);
        // attacks change as we level and get more powerful
        if(focus.equalsIgnoreCase(enemy.getName())){
        {
            misfire = false;
            if(caterpillar.getLevel() == 2){
                enemy.setHealth(enemy.getHealth() - caterpillar.getStrength() - strengthFactor() - 5);
                caterpillar.setLastAction("You attacked the " + focus + " with odor attack, sick!");
            }
            else if (caterpillar.getLevel() == 3 ) {
                enemy.setHealth(enemy.getHealth() - caterpillar.getStrength() - strengthFactor() - 10);
                caterpillar.setLastAction("You attacked the " + focus + " with acid attack, burn!" );
            }
            else if(caterpillar.getLevel() == 1){
                enemy.setHealth(enemy.getHealth() - caterpillar.getStrength() - strengthFactor());
                caterpillar.setLastAction("You attacked the " + focus + " with tackle attack, bruised!" );
            }
            }
        }
        if(enemy.getHealth() <= 0){
            enemy.setHidden(true);
            enemy.setInCombat(false);
            caterpillar.setExperience(caterpillar.getExperience() + 10);
            caterpillar.levelUp();
            if(caterpillar.getLastAction().contains("level")){
                caterpillar.setLastAction("You have defeated the mighty " + enemy.getName() + "\n " + caterpillar.getLastAction());
            }

        }
        else{
            //TODO: print status of enemy
        }
        enemyAttack();
        if(caterpillar.getHealth() == 0){
            //TODO: You have died.
        }
    }

    private void enemyAttack(){
        caterpillar.setHealth(caterpillar.getHealth() - enemy.getStrength());
    }

    private int strengthFactor(){
        int strength = 0;
        if (caterpillar.getStrength() > enemy.getStrength()) {
            strength = 5;
        }
        else if (caterpillar.getStrength() < enemy.getStrength()){
            strength = -5;
        }
        return strength;
    }
    private void processAntAssistance(String focus) {
        if (focus.equalsIgnoreCase("ANT") && caterpillar.getLevel() == 2)  {
            //DONE : Implement "Ant can be used in combat" logic here.
            caterpillar.setStrength(caterpillar.getStrength() + 60);
            caterpillar.setLastAction("You have received assistance from a friendly ant");
            misfire = false;
        }
    }
    private void processRun(String focus){
        if(focus.toUpperCase(Locale.ROOT).equalsIgnoreCase("RUN")){
            misfire = false;
            if(caterpillar.getStrength() > enemy.getStrength() && enemy.isAggressive() == true ){
                caterpillar.setLastAction("You ran away from the fight despite enemy efforts to subdue you");
                enemy.setHidden(true);
                enemy.setInCombat(false);
            }
            else if (caterpillar.getStrength() > enemy.getStrength() && enemy.isAggressive() == false) {
                caterpillar.setLastAction("You ran away from the fight and live another day");
                enemy.setHidden(true);
                enemy.setInCombat(false);
            }
            else if (caterpillar.getStrength() < enemy.getStrength() && enemy.isAggressive() == false){
                caterpillar.setLastAction("You were lucky this time, the enemy gave up its pursuit");
                enemy.setHidden(true);
                enemy.setInCombat(false);
                }
            else{
                caterpillar.setLastAction("Oh no, you can't escape, you are forced to fight!");
                enemy.setInCombat(true);
            }

        }
    }
    private void processLeave(String focus) {
    }

    private void processHide(String focus) {
    }


    private void processEating(String focus) {
        switch(focus.toLowerCase()){
            case "leaf":
                caterpillar.eat(caterpillar.getCurrentLocation().getLeaf());
                if(!caterpillar.getLastAction().contains("level")){
                    caterpillar.setLastAction("You eat a leaf!");
                }
                misfire = false;
        }
    }

    private void processNavigation(String focus) {
        switch(focus){
            case "north":
                if(!caterpillar.getCurrentLocation().getNorth().trim().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getNorth().trim()));
                    caterpillar.setLastAction("You travel north.");
                    misfire = false;
                }
                break;
            case "south":
                if(!caterpillar.getCurrentLocation().getSouth().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getSouth().trim()));
                    caterpillar.setLastAction("You travel south.");
                    misfire = false;
                }
                break;
            case "east":
                if(!caterpillar.getCurrentLocation().getEast().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getEast().trim()));
                    caterpillar.setLastAction("You travel east.");
                    misfire = false;
                }
                break;
            case "west":
                if(!caterpillar.getCurrentLocation().getWest().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getWest().trim()));
                    caterpillar.setLastAction("You travel west.");
                    misfire = false;
                }
                break;
        }
    }
}

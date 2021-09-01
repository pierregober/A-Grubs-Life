package com.game.model.engine;


import com.game.model.materials.Caterpillar;
import com.game.model.materials.Combat;
import com.game.model.materials.Enemy;
import com.game.model.materials.Location;

import java.util.*;

public class CommandProcessor {
    private Caterpillar caterpillar;
    private HashMap<String, Location> locations;
    private HashMap<String, Enemy> enemies;
    private boolean begin;
    Enemy enemy = enemies.get(caterpillar.getCurrentLocation().getEnemy().getName()); // shortcut for finding your enemy

    public CommandProcessor(Caterpillar caterpillar, HashMap<String,Location> locations, HashMap<String, Enemy> enemies){
        this.caterpillar = caterpillar;
        this.locations = locations;
        this.enemies = enemies;
        this.begin = false;
    }

    public void executeCommand(ArrayList<String> strings) {
        if (strings.size() == 2 && strings.get(0) != null && strings.get(1) != null) {


            String action = strings.get(0).toUpperCase(Locale.ROOT);
            String focus = strings.get(1).toUpperCase(Locale.ROOT);

            System.out.println(action + focus);
            if (enemy.isInCombat()) {
                if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("ATTACK")) {
                    processAttack(action, focus);
                    if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("HELP")) {
                        processAntAssistance(focus);
                    }
                } else if (focus.toUpperCase(Locale.ROOT).equalsIgnoreCase("RUN")) {
                    processRun(focus);
                }

            } else {
                if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("GO")) {
                    processNavigation(focus.toLowerCase());
                } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("EAT")) {
                    processEating(focus);
                } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("HIDE")) {
                    processHide(focus);
                } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("LEAVE")) {
                    processLeave(focus);
                } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("ATTACK")) {
                    processAttack(action, focus);
                }else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("RUN")) {
                    processAttack(action, focus);
                } else {
                }
            }
        } else {
            processTypo();
        }
    }

    private void processTypo() {
        if (!caterpillar.getLastAction().contains(" \n You need to use a verb noun model like this : [go north]")) {
            caterpillar.setLastAction(caterpillar.getLastAction() + " \n You need to use a verb noun model like this : [go north]");
        }
    }



    private void processMisuse(){
        if(!caterpillar.getLastAction().contains("\n You can't do that here.")){
            caterpillar.setLastAction(caterpillar.getLastAction()  + "\n You can't do that here.");
        }

    }




    private void processAttack(String action, String focus) {
        enemy.setInCombat(true);
        // attacks change as we level and get more powerful
        if( action.equalsIgnoreCase("ATTACK") && focus.equalsIgnoreCase(String.valueOf(enemies.get(enemy)))){
        {
            if(caterpillar.getLevel() > 4 && caterpillar.getLevel()< 10){
                enemy.setHealth(enemy.getHealth() - caterpillar.getStrength() - strengthFactor() - 5);
                caterpillar.setLastAction("You attacked the " + focus + " with odor attack, sick!");
            }
            else if (caterpillar.getLevel() > 9 ) {
                enemy.setHealth(enemy.getHealth() - caterpillar.getStrength() - strengthFactor() - 10);
                caterpillar.setLastAction("You attacked the " + focus + " with acid attack, burn!" );
            }
            else{
                enemy.setHealth(enemy.getHealth() - caterpillar.getStrength() - strengthFactor());
                caterpillar.setLastAction("You attacked the " + focus + " with tackle attack, bruised!" );
            }
            }
        }
        if(enemy.getHealth() == 0){
            enemy.setHidden(true);
            enemy.setInCombat(false);
            caterpillar.setLastAction("You have defeated the mighty " + enemy.getName());
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
        if (focus.toUpperCase(Locale.ROOT).equalsIgnoreCase("COMBAT") && caterpillar.getLevel() == 2)  {
            //DONE : Implement "Ant can be used in combat" logic here.
            caterpillar.setStrength(caterpillar.getStrength() + 60);
            caterpillar.setLastAction("You have received assistance from a friendly ant");
        }
    }
    private void processRun(String focus){
        if(focus.toUpperCase(Locale.ROOT).equalsIgnoreCase("RUN")){
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
                caterpillar.setLastAction("You eat a leaf!");
        }
    }

    private void processNavigation(String focus) {
        switch(focus){
            case "north":
                if(!caterpillar.getCurrentLocation().getNorth().trim().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getNorth().trim()));
                    caterpillar.setLastAction("You travel north.");
                }else{
                    processMisuse();
                }
                break;
            case "south":
                if(!caterpillar.getCurrentLocation().getSouth().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getSouth().trim()));
                    caterpillar.setLastAction("You travel south.");
                }else{
                    processMisuse();
                }
                break;
            case "east":
                if(!caterpillar.getCurrentLocation().getEast().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getEast().trim()));
                    caterpillar.setLastAction("You travel east.");
                }else{
                    processMisuse();
                }
                break;
            case "west":
                if(!caterpillar.getCurrentLocation().getWest().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getWest().trim()));
                    caterpillar.setLastAction("You travel west.");
                }else{
                    processMisuse();
                }
                break;
        }
    }
}

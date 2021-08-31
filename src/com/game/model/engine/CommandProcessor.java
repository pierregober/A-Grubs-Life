package com.game.model.engine;


import com.game.model.materials.Caterpillar;
import com.game.model.materials.Enemy;
import com.game.model.materials.Location;

import java.util.*;

public class CommandProcessor {
    private Caterpillar caterpillar;
    private HashMap<String, Location> locations;
    private HashMap<String, Enemy> enemies;
    public CommandProcessor(Caterpillar caterpillar, HashMap<String,Location> locations, HashMap<String, Enemy> enemies){
        this.caterpillar = caterpillar;
        this.locations = locations;
        this.enemies = enemies;
    }

    public void executeCommand(ArrayList<String> strings) {
        String action = strings.get(0).toUpperCase(Locale.ROOT);
        String focus = strings.get(1).toUpperCase(Locale.ROOT);

        System.out.println(action + focus);
        if(action.toUpperCase(Locale.ROOT).equalsIgnoreCase("GO")){
            processNavigation(focus.toLowerCase());
        }else if(action.toUpperCase(Locale.ROOT).equalsIgnoreCase("EAT")){
            processEating(focus);
        }else if(action.toUpperCase(Locale.ROOT).equalsIgnoreCase("ATTACK")){
            processAttack(focus);
        }else if(action.toUpperCase(Locale.ROOT).equalsIgnoreCase("HIDE")){
            processHide(focus);
        }else if(action.toUpperCase(Locale.ROOT).equalsIgnoreCase("LEAVE")){
            processLeave(focus);
        }else if(action.toUpperCase(Locale.ROOT).equalsIgnoreCase("TAME")){
            processTame(focus);
        }else{
            //sout maybe
        }

    }

    private void processAttack(String focus) {

    }

    private void processLeave(String focus) {
    }

    private void processHide(String focus) {
    }

    private void processTame(String focus){
        if(focus.toUpperCase(Locale.ROOT).equalsIgnoreCase("ANT") && caterpillar.getCurrentLocation().getName().equalsIgnoreCase("hill")){
            caterpillar.setStrength(caterpillar.getStrength() + 30);
            caterpillar.setLastAction("You have tamed the fierce ant!!!! You gained 30 strength with your new companion.");
        }
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
                if(!caterpillar.getCurrentLocation().getNorth().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getNorth().trim()));
                    caterpillar.setLastAction("You travel north.");
                    break;
                }

            case "south":
                if(!caterpillar.getCurrentLocation().getSouth().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getSouth().trim()));
                    caterpillar.setLastAction("You travel south.");
                    break;
                }
            case "east":
                if(!caterpillar.getCurrentLocation().getEast().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getEast().trim()));
                    caterpillar.setLastAction("You travel east.");
                    break;
                }

            case "west":
                if(!caterpillar.getCurrentLocation().getWest().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getWest().trim()));
                    caterpillar.setLastAction("You travel west.");
                    break;
                }
        }
    }

}

package com.game.model.engine;


import com.game.model.materials.Caterpillar;
import com.game.model.materials.Enemy;
import com.game.model.materials.Location;

import java.util.*;

public class CommandProcessor {
    private Caterpillar caterpillar;
    private HashMap<String, Location> locations;
    private HashMap<String, Enemy> enemies;
    private boolean begin;


    public CommandProcessor(Caterpillar caterpillar, HashMap<String,Location> locations, HashMap<String, Enemy> enemies){
        this.caterpillar = caterpillar;
        this.locations = locations;
        this.enemies = enemies;
        this.begin = false;
    }

    public void executeCommand(ArrayList<String> strings) {
        if(strings.size() == 2  &&   strings.get(0) != null && strings.get(1) != null ) {


            String action = strings.get(0).toUpperCase(Locale.ROOT);
            String focus = strings.get(1).toUpperCase(Locale.ROOT);

            System.out.println(action + focus);
            if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("GO")) {
                processNavigation(focus.toLowerCase());
            } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("EAT")) {
                processEating(focus);
            } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("ATTACK")) {

                processAttack(focus);

                if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("HELP")) {
                    processAntAssistance(focus);
                }

            } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("HIDE")) {
                processHide(focus);
            } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("LEAVE")) {
                processLeave(focus);
            } else if (action.toUpperCase(Locale.ROOT).equalsIgnoreCase("TAME")) {

            }else{
                processMisuse();
            }
        } else{
            processTypo();
        }
    }

    private void processTypo() {
        if(!caterpillar.getLastAction().contains(" \n You need to use a verb noun model like this : [go north]")){
            caterpillar.setLastAction(caterpillar.getLastAction() + " \n You need to use a verb noun model like this : [go north]");
        }
    }

    private void processMisuse(){
        if(!caterpillar.getLastAction().contains("\n You can't do that here.")){
            caterpillar.setLastAction(caterpillar.getLastAction()  + "\n You can't do that here.");
        }

    }

    private void processAttack(String focus) {
    }

    private void processAntAssistance(String focus) {
        if (focus.toUpperCase(Locale.ROOT).equalsIgnoreCase("COMBAT")) {
            //TODO : Implement "Ant can be used in combat logic here.
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

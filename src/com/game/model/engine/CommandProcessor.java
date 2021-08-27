package com.game.model.engine;


import com.game.model.materials.Caterpillar;
import com.game.model.materials.Location;

import java.util.*;

public class CommandProcessor {
    private Caterpillar caterpillar;
    private HashMap<String, Location> locations;
    public CommandProcessor(Caterpillar caterpillar, HashMap<String,Location> locations){
        this.caterpillar = caterpillar;
        this.locations = locations;
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
            processMate(focus);
        }else if(action.toUpperCase(Locale.ROOT).equalsIgnoreCase("HIDE")){
            processHide(focus);
        }else if(action.toUpperCase(Locale.ROOT).equalsIgnoreCase("LEAVE")){
            processLeave(focus);
        }

    }

    private void processLeave(String focus) {
    }

    private void processHide(String focus) {
    }

    private void processMate(String focus) {
    }

    private void processEating(String focus) {
        switch(focus.toLowerCase()){
            case "leaf":
                caterpillar.setExperience(caterpillar.getExperience() + caterpillar.getCurrentLocation().getLeaf().getXp());
        }
    }

    private void processNavigation(String focus) {
        switch(focus){
            case "north":
                if(!caterpillar.getCurrentLocation().getNorth().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getNorth().trim()));
                    break;
                }

            case "south":
                if(!caterpillar.getCurrentLocation().getSouth().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getSouth().trim()));
                    break;
                }
            case "east":
                if(!caterpillar.getCurrentLocation().getEast().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getEast().trim()));
                    break;
                }

            case "west":
                if(!caterpillar.getCurrentLocation().getWest().equalsIgnoreCase("DEAD_END")){
                    caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getWest().trim()));
                    break;
                }
        }
    }

}

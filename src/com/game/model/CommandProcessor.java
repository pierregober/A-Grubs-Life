package com.game.model;


import java.util.*;

public class CommandProcessor {
    private Caterpillar caterpillar;
    private HashMap<String,Location> locations;
    public CommandProcessor(Caterpillar caterpillar, HashMap<String,Location> locations){
        this.caterpillar = caterpillar;
        this.locations = locations;
    }
    public String decipherVerbs() {

        String verb = "";
        String output = "";
        //Hardcoding userInput for now. We should get userInput from TextParser class
        Collection<String> commands = new ArrayList<>();

        if(!commands.contains(verb)) {
            output = "You have entered an invalid verb";
        }
        else {
            switch (verb) {
                case "take":
                    output = takeSomething();
                    break;
                case "look":
                    output = lookSomething();
                    break;
                case "drop":
                    output = dropSomething();
                    break;
            }
        }
        return output;
    }

    private String dropSomething() {
        //Implement description here of the here
        return "value";
    }

    private String lookSomething() {
        //Implement description here of the here
        return "value";
    }

    private String takeSomething() {
        //Implement description here of the here
        return "value";
    }

    public String decipherNoun() {

        String noun = "";
        String output = "";
        //Hardcoding userInput for now. We should get userInput from TextParser class
        Collection<String> commands = new ArrayList<>();

        if(!commands.contains(noun)) {
            output = "You have entered an invalid noun";
        }
        else {
            switch (noun) {
                case "north":
                    output = "You are now in the north";
                    break;
                case "south":
                    output = "You are now in the south";
                    break;
                case "east":
                    output = "you are now in the east";
                    break;
                case "west":
                    output = "You are now in the west";
            }
        }
        return output;
    }

    public void executeCommand(ArrayList<String> strings) {
        String action = strings.get(0).toUpperCase(Locale.ROOT);
        String focus = strings.get(1).toUpperCase(Locale.ROOT);
        String deadEnd = "DEAD_END";
        if(action.equalsIgnoreCase("go") && focus.equalsIgnoreCase("north") &&
            !caterpillar.getCurrentLocation().getNorth().equalsIgnoreCase(deadEnd)){
            caterpillar.setCurrentLocation(locations.get(caterpillar.getCurrentLocation().getNorth()));
        }
    }

}

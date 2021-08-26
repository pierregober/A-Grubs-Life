package com.game.model;


import java.util.ArrayList;
import java.util.Collection;

public class LogicEngine {

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

}

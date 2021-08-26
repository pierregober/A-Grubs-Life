package com.game.controller;


import com.game.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Game {



    public Game() {
    }

    public void start(){
        //instantiate model objects
        Location loc = new Location("GENESIS","THIS IS YOUR BIRTHPLACE", "WOODS", "DEAD_END", "DEAD_END", "DEAD_END");
        Location loc2 = new Location("WOODS", "THE WOODS","DEAD_END","GENESIS","DEAD_END","DEAD_END");
        HashMap<String, Location> locations = new HashMap<>();
        locations.put(loc.getName(),loc);
        locations.put(loc2.getName(),loc2);


        Caterpillar caterpillar = new Caterpillar(100,0,0);
        caterpillar.setCurrentLocation(loc);
        Prompter prompter = new Prompter();
        TextParser parser = new TextParser();
        KeyWordIdentifier kwi = new KeyWordIdentifier();
        CommandProcessor commandProcessor = new CommandProcessor(caterpillar,locations);


        //Welcome Screen goes here.
        welcome();
       // createPlayer();
        while (true){
            // logic will go here. To loop through.
            System.out.println(caterpillar.getCurrentLocation().getName());
            String userInput = prompter.getInput();
            ArrayList parsedInput = parser.parseInput(userInput);
            ArrayList command = kwi.identifyKewWords(parsedInput);
            commandProcessor.executeCommand(command);// << updates caterpillar
            System.out.println(caterpillar.getCurrentLocation().getName());




            break;
        }
        quit();

    }

    private void welcome(){

        System.out.println("Welcome!! \n");
        System.out.println("How to Play: " +
                "\n1. Instructions.\n" );

    }
    private void quit(){
        System.out.println("You are leaving the game. Good Bye.");
        System.exit(0);
    }

    private void createPlayer(){

    }
}
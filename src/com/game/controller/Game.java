package com.game.controller;


import com.game.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    public Game() {

    }

    public void start(){
        //instantiate model objects
        HashMap<String, Location> locations = populateLocations();
        Caterpillar caterpillar = new Caterpillar(100,0,0);
        Prompter prompter = new Prompter();
        TextParser parser = new TextParser();
        KeyWordIdentifier kwi = new KeyWordIdentifier();
        CommandProcessor commandProcessor = new CommandProcessor(caterpillar,locations);
        caterpillar.setCurrentLocation(populateLocations().get("GENESIS"));


        //Welcome Screen goes here.
        welcome();
       // createPlayer();
        while (true){
            // logic will go here. To loop through.
            String userInput = prompter.getInput();
            ArrayList parsedInput = parser.parseInput(userInput);
            ArrayList command = kwi.identifyKewWords(parsedInput);
            commandProcessor.executeCommand(command);// << updates caterpillar
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

    private HashMap<String,Location> populateLocations(){

        HashMap<String,Location> locations = new HashMap<>();
        String[] locationFields = new String[6];
        try{
            File file = new File("locations.txt");
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                locationFields = myReader.nextLine().split(",");
                Location loc = new Location(locationFields[0],locationFields[ 1], locationFields[ 2], locationFields[ 3], locationFields[4],locationFields[ 5] );
                locations.put(locationFields[0], loc);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            }
        return locations;
    }
}
package com.game.controller;


import com.game.model.engine.CommandProcessor;
import com.game.model.engine.KeyWordIdentifier;
import com.game.model.engine.Prompter;
import com.game.model.engine.TextParser;
import com.game.model.materials.Caterpillar;
import com.game.model.materials.Leaf;
import com.game.model.materials.Location;
import com.game.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public Game() {

    }

    public void start(){
        //instantiate view
        View view = new View();

        //instantiate model objects
        boolean running = true;
        HashMap<String, Location> locations = populateLocations();
        Caterpillar caterpillar = new Caterpillar(100,0,0);
        Prompter prompter = new Prompter();
        TextParser parser = new TextParser();
        KeyWordIdentifier kwi = new KeyWordIdentifier();
        CommandProcessor commandProcessor = new CommandProcessor(caterpillar,locations);
        caterpillar.setCurrentLocation(locations.get("GENESIS"));


        view.printWelcomeMessage();
        view.printInstructions();

        //+++++++++++++++  GAME LOOP  +++++++++++++++++++ should be its own method
        while (running){
            //clearScreen();
            view.printUpdate(caterpillar);
            System.out.println(caterpillar.getExperience());
            // logic will go here. To loop through.
            view.promptUser();
            String userInput = prompter.getInput();
            if(userInput.equalsIgnoreCase("quit")){
                quit(view);
            }else{
                ArrayList parsedInput = parser.parseInput(userInput);
                ArrayList command = kwi.identifyKewWords(parsedInput);
                commandProcessor.executeCommand(command);// << updates caterpillar
            }
        }


    }


    private void quit(View view){
        view.printQuit();
        System.exit(0);
    }
    private void createPlayer(){

    }

    private HashMap<String,Location> populateLocations(){
        HashMap<String,Location> locations = new HashMap<>();
        HashMap<String, Leaf>  leaves = new HashMap<>();
        String[] locationFields;
        try{
            File file = new File("locations.txt");
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                locationFields = myReader.nextLine().split(",");

                Location loc = new Location(locationFields[0].trim(),locationFields[ 1].trim(), locationFields[ 2].trim(), locationFields[ 3].trim(), locationFields[4].trim(),locationFields[ 5].trim() );
                locations.put(locationFields[0].trim(), loc);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            }
        return locations;
    }
    private void clearScreen(){
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder process = (os.contains("windows")) ?
                new ProcessBuilder("cmd", "/c", "cls") :
                new ProcessBuilder("clear");
        try {
            process.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException ignored) {
        }
    }

}
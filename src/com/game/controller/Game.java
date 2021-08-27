package com.game.controller;


import com.game.model.engine.CommandProcessor;
import com.game.model.engine.KeyWordIdentifier;
import com.game.model.engine.Prompter;
import com.game.model.engine.TextParser;
import com.game.model.materials.Caterpillar;
import com.game.model.materials.Location;
import com.game.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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


        //+++++++++++++++  GAME LOOP  +++++++++++++++++++ should be its own method
        while (running){
            view.printCurrentRoom(caterpillar);
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
}
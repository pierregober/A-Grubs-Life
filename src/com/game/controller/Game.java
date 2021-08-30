package com.game.controller;


import com.game.model.engine.*;
import com.game.model.materials.Caterpillar;
import com.game.model.materials.Enemy;
import com.game.model.materials.Leaf;
import com.game.model.materials.Location;
import com.game.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private HashMap<String, Location> locations;
    private HashMap<String, Enemy> enemies;
    private Caterpillar caterpillar;
    private LogicEngine processor;
    private View view;
    public Game() {

    }

    public void start(){

        setUpComponents();
        run(caterpillar,processor,view);

    }

    private void setUpComponents(){
        this.view = new View();
        this.locations = populateLocations();
        this.enemies = populateEnemies();
        this.caterpillar = new Caterpillar(100,0,0);
        this.processor = new LogicEngine(caterpillar,locations);
        this.caterpillar.setCurrentLocation(locations.get("GENESIS"));

    }
    private void run(Caterpillar caterpillar, LogicEngine processor, View view){
        boolean running = true;
        view.printWelcomeMessage();
        view.printInstructions();
        while (running){
            view.printUpdate(caterpillar);
            System.out.println(caterpillar.getExperience());
            view.promptUser();
            String userInput = processor.getPrompter().getInput();
            if(userInput.equalsIgnoreCase("quit")){
                quit(view);
            }else{
                processor.processCommand(userInput);
            }
        }

    }

    private void quit(View view){
        view.printQuit();
        System.exit(0);
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
    private HashMap<String,Enemy> populateEnemies(){
        HashMap<String, Enemy> enemies = new HashMap<>();

        String[] enemyFields;
        try{
            File file = new File("enemies.txt");
            Scanner myReader = new Scanner(file);

            while(myReader.hasNextLine()){
                enemyFields = myReader.nextLine().split(",");

                Enemy enemy = new Enemy(enemyFields[0].trim(),Integer.parseInt(enemyFields[ 1].trim()), Integer.parseInt(enemyFields[ 2].trim()), Integer.parseInt(enemyFields[ 3].trim()), Boolean.parseBoolean(enemyFields[4].trim()), Boolean.parseBoolean(enemyFields[5].trim()), enemyFields[6].trim());
                enemies.put(enemyFields[6].trim(), enemy);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return enemies;
    }
}
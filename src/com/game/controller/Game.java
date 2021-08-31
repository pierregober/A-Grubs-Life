/*
    This is the game class. It instantiates all of the games materials, and updates them. The game will use the model package and view package to pass data from the user to the system and vice versa.
 */
package com.game.controller;


import com.game.model.engine.LogicEngine;
import com.game.model.materials.Caterpillar;
import com.game.model.materials.Enemy;
import com.game.model.materials.Location;
import com.game.view.ViewWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private HashMap<String, Location> locations;
    private HashMap<String, Enemy> enemies;
    private Caterpillar caterpillar;
    private LogicEngine processor;
    private ViewWindow viewWindow;
    public Game() {

    }
    //This should be called by the client to start a new game.
    public void start(){
        setUpComponents();
        run();
    }

    //This method is designed to instantiate the necessary fields of a Game object.
    private void setUpComponents(){
        this.locations = populateLocations();
        this.enemies = populateEnemies();
        this.caterpillar = new Caterpillar(100,0,0);
        this.processor = new LogicEngine(caterpillar,locations, enemies);
        this.caterpillar.setCurrentLocation(locations.get("GENESIS"));
        this.viewWindow = new ViewWindow(caterpillar, processor);
    }
    //This class controls the game loop. As the user inputs information the view will be updated.
    private void run(){
        boolean running = true;
        viewWindow.welcomeMessage();
        viewWindow.giveInstructions();
        int counter = 0;

        while (running){
            viewWindow.updateCaterpillarStatus();
            caterpillar.healthRegenerator(counter++);



        }

    }

    //This is a private helper method to read in all of the locations in a text file and parse them to ingame Location objects.
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
    //This is a private helper method to populate Enemy objects from an external text file.
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
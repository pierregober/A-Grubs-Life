package com.game.controller;


import com.game.model.*;

import java.util.ArrayList;

public class Game {



    public Game() {
    }

    public void start(){
        //instantiate model objects
        Caterpillar caterpillar = new Caterpillar();
        Prompter prompter = new Prompter();
        TextParser parser = new TextParser();
        KeyWordIdentifier kwi = new KeyWordIdentifier();
        CommandProcessor commandProcessor = new CommandProcessor(caterpillar);


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
}
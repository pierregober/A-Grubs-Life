package com.game.controller;


public class Game {



    public Game() {
    }

    public void start(){
        //Welcome Screen goes here.
        welcome();
        createPlayer();
        while (true){
            // logic will go here. To loop through.
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
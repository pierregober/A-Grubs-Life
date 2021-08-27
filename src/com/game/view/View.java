package com.game.view;

import com.game.model.materials.Caterpillar;

public class View {
    //takes in strings and prints em to the game
    public View(){

    }
    public void printInstructions(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++---------INSTRUCTIONS----------------++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++\n");

        System.out.println("Enter text in a (verb/noun) format to interact with the world.");
        System.out.println("As a caterpillar you will need to find food, " +
                           "transform into a butterfly, and find a mate!\n " +
                           "Be careful! Your environment might be more dangerous than you think...");
    }
    public void printCurrentItems(Caterpillar caterpillar) {

    }
    public void printWelcomeMessage(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++---------A  Grub's  Life-------------++");
        System.out.println("++-------------------------------------++");
        System.out.println("++--------0o0o0o0o0o0e-----------------++");
        System.out.println("++-------------------------------------++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println();
        System.out.println("A baby caterpillar emerges from its egg...");

        System.out.println();
    }
    public void printCurrentRoom(Caterpillar caterpillar) {
        String location = caterpillar.getCurrentLocation().getName().toLowerCase();
        String desc = caterpillar.getCurrentLocation().getDescription().toLowerCase();

        System.out.println("The caterpillar enters the " + location
                            + ". "  + "\nAs he looks around, he sees "
                            + desc + ".");
        System.out.println("-----------------------------------------");
        //maybe above this line in that string we can add some
    }

    public void printCaterpillarStats(Caterpillar caterpillar) {
        System.out.println(caterpillar.getExperience());
    }
    public void printQuit(){
        System.out.println("You are leaving the game. Good Bye.");
    }
    public  void promptUser(){
        System.out.println("Please enter a command [Noun / Verb]: ");
    }

    public void printUpdate(Caterpillar caterpillar) {
        printCaterpillarStats(caterpillar);
        printCurrentRoom(caterpillar);
    }
}

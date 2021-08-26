package com.game.model;

import java.util.Scanner;

public class Prompter {
    private Scanner scanner;
    public Prompter(){
        scanner = new Scanner(System.in);
    }


    public String getInput(){
        System.out.println("Please enter a command: ");
        String result = scanner.nextLine();
        return result;
    }
}

package com.game.model.engine;

import java.util.Scanner;

public class Prompter {
    private Scanner scanner;
    public Prompter(){
        scanner = new Scanner(System.in);
    }


    public String getInput(){

        String result = scanner.nextLine();
        return result;
    }
}

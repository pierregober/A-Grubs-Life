package com.game.model;
//This class should validate a verb and a noun given by the user has come

import java.util.Scanner;

public class TextParser {
    private String verb;
    private String noun;
    private Scanner scanner;

    public TextParser(){
        scanner = new Scanner(System.in);
        this.verb = scanner.next();
        this.noun = scanner.next();
        scanner.close();
    }

    public String getNoun() {
        return this.noun;
    }

    public  String getVerb() {
        return this.verb;
    }

}

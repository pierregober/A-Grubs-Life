package com.game.model;
//This class should validate a verb and a noun given by the user has come

import java.util.HashSet;
import java.util.Scanner;

public class TextParser {
    private String verb;
    private String noun;
    private Scanner scanner;
    private HashSet approvedVerbs;
    private HashSet approvedNouns;

    public TextParser(){
        this.scanner = new Scanner(System.in);
        promptUserInput();

    }
    //If this noun is in our approved nouns then we set it otherwise
    public String getNoun() {
        return this.noun;
    }

    public  String getVerb() {
        return  this.verb;
    }

    public void setVerb(String str){
        this.verb = str;
    }
    public void setNoun(String str){
        this.noun = str;
    }
    public void promptUserInput(){
        System.out.println("Please enter a command!:  ");
        setVerb(scanner.next());
        setNoun(scanner.next());
    }
}

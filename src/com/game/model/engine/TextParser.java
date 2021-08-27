package com.game.model.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

public class TextParser {
    private HashSet<String> verbs;
    private HashSet<String> nouns;
    public TextParser(){
         super();
         populateVerbs();
         populateNouns();

    }

    private void populateNouns() {
        this.nouns = new HashSet();
        nouns.add("NORTH");
        nouns.add("LEAF");
    }

    private void populateVerbs() {
        this.verbs = new HashSet();
        verbs.add("GO");
        verbs.add("EAT");
    }


    //If we dont get a viable verb and noun then we will pass null.
    public ArrayList<String> parseInput(String unParsedCommand) {
        String[] result;
        result = unParsedCommand.toUpperCase(Locale.ROOT).split(" ");
        ArrayList<String> list = new ArrayList<>();

        for(String str : result){
            if(verbs.contains(str.toUpperCase(Locale.ROOT)) || nouns.contains(str.toUpperCase(Locale.ROOT))){
                list.add(str);
                if(list.size() == 2){
                    return list;
                }
            }
        }
        return null;
    }
}

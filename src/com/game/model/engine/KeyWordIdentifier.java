package com.game.model.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

//grab appropriate command (verb, noun)
public class KeyWordIdentifier {

    HashSet<String> keyWords;
    public KeyWordIdentifier(){
        keyWords = new HashSet<>();
        keyWords.add("GO");
        keyWords.add("NORTH");
    }


    public ArrayList<String> identifyKewWords(ArrayList<String> parsedInput) {
         ArrayList<String> result = new ArrayList();

         for(int i = 0; i < parsedInput.size(); i++){
             if(!keyWords.contains(parsedInput.get(i))){
                 return null;
             }else{
                 result.add(parsedInput.get(i).toUpperCase(Locale.ROOT));
             }
         }
         return result;
    }
}

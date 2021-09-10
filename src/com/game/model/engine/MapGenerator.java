package com.game.model.engine;

import com.game.model.materials.Caterpillar;

import java.io.*;

public class MapGenerator {
    private Caterpillar caterpillar;

    public MapGenerator(Caterpillar caterpillar) {
        this.caterpillar = caterpillar;
    }
    public void makeFile() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("map.txt")));
            PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream("C:\\StudentWork\\Sprint\\A-Grubs-Life\\src\\com\\game\\model\\engine\\currentLocation.txt")));

            String line;

            while((line = br.readLine() )!= null){
                if (line.contains(("[" + caterpillar.getCurrentLocation().getName() + "]"))) {
                    line = line.replace("[" + caterpillar.getCurrentLocation().getName() + "]", "<PLAYER>");
                }
                writer.println(line);
            }
            writer.close();
            br.close();
            System.out.println("File Created");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

//    public void displayFile(String map) {
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(map)));
//            String line;
//
//            System.out.println("DISPLAY FILE FUNCTION");
//            while((line = br.readLine() )!= null){
//                System.out.println(line);
//            }
//        }
//        catch (FileNotFoundException e){
//            System.out.println("cannot find file");
//        }
//        catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//    }

    public static String displayFile(String map) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(MapGenerator.class.getResourceAsStream(map)));
            String line = null;

            while((line = br.readLine() )!= null){
                line = line.replace(" ",  "&nbsp;");
                contentBuilder.append("<p>").append(line).append("</p>");
            }
            //Step 3: Make sure to close the connections if done!
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println("cannot find file");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return contentBuilder.toString();
    }

}
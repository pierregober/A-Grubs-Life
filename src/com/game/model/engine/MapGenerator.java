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

    public void displayFile() {
        File file = new File("C:\\StudentWork\\Sprint\\A-Grubs-Life\\src\\com\\game\\model\\engine\\currentLocation.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null)
                System.out.println(st);
        }
        catch (FileNotFoundException e){
            System.out.println("cannot find file");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteFile(String file){
        File myObj = new File(file);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}
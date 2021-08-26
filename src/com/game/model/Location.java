package com.game.model;

import java.util.HashSet;

public class Location {
    private String name;
    private String description;
    private String north;
    private String south;
    private String east;
    private String west;



    public Location(String name, String description, String north, String south, String east, String west){
        this.name = name;
        this.description = description;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNorth() {
        return north;
    }

    public String getSouth() {
        return south;
    }

    public String getEast() {
        return east;
    }

    public String getWest() {
        return west;
    }
}

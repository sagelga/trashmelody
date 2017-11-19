package com.trashmelody.models;

public class Building {
    private String name;
    private String beatmapGroupId;

    public Building(String name, String beatmapGroupId) {
        this.name = name;
        this.beatmapGroupId = beatmapGroupId;
    }

    public String getName() {
        return name;
    }

    public String getBeatmapGroupId() {
        return beatmapGroupId;
    }
}

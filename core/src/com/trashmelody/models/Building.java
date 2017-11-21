package com.trashmelody.models;

import com.badlogic.gdx.audio.Music;

public class Building {
    private String name;
    private String beatmapGroupId;
    private String easyBeatmapId;
    private String mediumBeatmapId;
    private String hardBeatmapId;
    public int highScore;

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

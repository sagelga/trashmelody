package com.trashmelody.managers;

import java.io.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class StatsManager {

    private Preferences preferences = getPrefs();

    /* Preference current HashMaps
    Key | Value Type
   currentStage | integer   // For setting the current stage
   stage1Score | integer // For stage 1 high score
   stage2Score | integer // For stage 2 high score
   stage3Score | integer // For stage 3 high score
   stage4Score | integer // For stage 4 high score
   stage5Score | integer // For stage 5 high score
   stage6Score | integer // For stage 6 high score
   currentMusicVolume |  float // For user preferred music volume
   currentMusicTrack | String // For user current music track
*/

    protected StatsManager() {
    }

    protected Preferences getPrefs() {
        if (preferences == null) {
            preferences = Gdx.app.getPreferences("TrashMelody");
        }
        preferences.flush();
        return preferences;
    }

    public void setScore(String stageID, int score) {
        // Sets the score, and keep it as a buffer
        preferences.putInteger(stageID, score);
        preferences.flush();
    }

    public int getScore(String stageID) {
        // Retrieves the score from the given user name
        return preferences.getInteger(stageID);
    }

    public int getCurrentStage() {
        return preferences.getInteger("currentStage");
    }

    public void setCurrentStage(String currentStage, int currentStageID) {
        preferences.putInteger(currentStage, currentStageID);
    }

    public void setCurrentMusicVolume(float volume) {
        preferences.putFloat("currentMusicVolume", volume);
        preferences.flush();
    }

    public float getCurrentMusicVolume() {
        return preferences.getFloat("currentMusicVolume");
    }

    public void setCurrentMusicTrack(String musicTrack) {
        preferences.putString("currentMusicTrack", musicTrack);
        preferences.flush();
    }

    public String getCurrentMusicTrack() {
        return preferences.getString("currentMusicTrack");
    }

    public void setStageScore(String stageID, int score) {
        if (stageID.startsWith("stage") && stageID.endsWith("Score")) {
            preferences.putInteger(stageID, score);
            preferences.flush();
        }
    }

    public int getStageScore(String stageID) {
        if (stageID.startsWith("stage") && stageID.endsWith("Score")) {
            return preferences.getInteger(stageID);
        }
        return -1;
    }


}


package com.trashmelody.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class StatsManager {

    @Inject
    public StatsManager() {
        getPreferences();
    }

    private Preferences preferences = getPreferences();

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
   recurrentUser | boolean // For checking that user is recurrent user or not
*/

    protected Preferences getPreferences() {
        preferences = Gdx.app.getPreferences("TrashMelody");
        preferences = Gdx.app.getPreferences("TrashMelody");

        // Initialize default data
        if (!getRecurrentUser()) {
            setCurrentStage(1);
            setCurrentMusicVolume(0.3F);
            setCurrentMusicTrack("MUSIC_BG1");
            setRecurrentUser();
        }
        preferences.flush();
        return preferences;
    }

    public int getCurrentStage() {
        return preferences.getInteger("currentStage");
    }

    public void setCurrentStage(int currentStageID) {
        preferences.putInteger("currentStage", currentStageID);
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

    // Methods to set the highscore stage score ---------------------------------------------------
    public void setStageScore(String stageID, int score) {
        if (stageID.startsWith("stage") && stageID.endsWith("Score")) {
            if (getStageScore(stageID) < score) {
                preferences.putInteger(stageID, score);
                preferences.flush();
            }
        }
    }

    public int getStageScore(String stageID) {
        return preferences.getInteger(stageID);
    }

    public int getScore(String beatmapId) {
        return preferences.getInteger(beatmapId + "score");
    }

    public void setScore(String beatmapId, int score) {
        preferences.putInteger(beatmapId + "score", score);
    }

    private void resetStageScore(String stageID) {
        setStageScore(stageID, 0);
    }

    public void setDevelopmentMode() {
        preferences.putBoolean("developMode", !(getDevelopmentMode()));
    }

    public boolean getDevelopmentMode(){
        return preferences.getBoolean("developMode");
    }

    // Other StatsManager utility methods
    public boolean getRecurrentUser() {
        return preferences.getBoolean("recurrentUser");
    }

    public void setRecurrentUser() {
        preferences.putBoolean("recurrentUser", true);
    }

}


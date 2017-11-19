package com.trashmelody.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.utils.Debugger;

import static com.trashmelody.managers.Assets.MUSIC;

@Singleton
public class MusicManager {
    private Assets assets;

    private static final float MUSIC_VOLUME_TOGGLE = 0.1F;
    private static final float MUSIC_DEFAULT_VOLUME = 0.5F;
    private static String currentMusic;

    @Inject
    public MusicManager(Assets assets) {
        this.assets = assets;
    }


    // Update the local variables
    private void resetVariable() {
        StatsManager statsManager = new StatsManager();
        Debugger.statsManager = statsManager;
        statsManager.setCurrentMusicTrack(currentMusic);
        statsManager.setCurrentMusicVolume(assets.get(currentMusic, MUSIC).getVolume());
    }

    // Set current music track for future use -----------------------------------------------------
    public void setDefault(String music) {
        currentMusic = music;
        setMusicLoopStatus(true);
        resetVariable();
    }

    // Set music states ---------------------------------------------------------------------------
    public void playMusic() {
        playMusic(currentMusic);
    }

    public void playMusic(String music) {
        playMusic(music, MUSIC_DEFAULT_VOLUME);
    }

    public void playMusic(float volume) {
        playMusic(currentMusic, volume);
    }

    public void playMusic(String music, float volume) {
        assets.get(music, MUSIC).play();
        assets.get(music, MUSIC).setVolume(volume);
        resetVariable();
    }

    public void pauseMusic(String music) {
        assets.get(music, MUSIC).pause();
    }

    public void stopMusic() {
        assets.get(currentMusic, MUSIC).stop();
    }

    public void stopMusic(String music) {
        assets.get(music, MUSIC).stop();
        resetVariable();
    }

    // Check Background Music Looping status ------------------------------------------------------
    public boolean getMusicLoopStatus(String music) {
        return assets.get(music, MUSIC).isLooping();
    }

    public boolean getMusicLoopStatus() {
        return getMusicLoopStatus(currentMusic);
    }

    // Setter Background Music Looping ------------------------------------------------------------
    public void setMusicLoopStatus(String music, boolean status) {
        assets.get(music, MUSIC).setLooping(status);
    }

    public void setMusicLoopStatus(boolean status) {
        setMusicLoopStatus(currentMusic, status);
    }

    // Check Background Music Playing status ------------------------------------------------------
    public boolean getMusicPlayStatus(String music) {
        return assets.get(music, MUSIC).isPlaying();
    }

    public boolean getMusicPlayStatus() {
        return getMusicPlayStatus(currentMusic);
    }

    // Toggling Background Music Volume -----------------------------------------------------------
    public float getBackgroundMusicVolume() {
        return assets.get(currentMusic, MUSIC).getVolume();
    }

    public void increaseBackgroundVolume() {
        increaseVolume(currentMusic);
    }

    public void increaseVolume(String music) {
        assets.get(music, MUSIC).setVolume(Math.min(assets.get(music, MUSIC).getVolume() + MUSIC_VOLUME_TOGGLE, 1F));
        resetVariable();
    }

    public void decreaseBackgroundVolume() {
        decreaseVolume(currentMusic);
    }

    public void decreaseVolume(String music) {
        assets.get(music, MUSIC).setVolume(Math.max(assets.get(music, MUSIC).getVolume() - MUSIC_VOLUME_TOGGLE, 0F));
        resetVariable();
    }

    // Set background Volume ----------------------------------------------------------------------
    public void setVolume() { // Use default value of music track + volume
        setVolume(currentMusic);
    }

    public void setVolume(float volume) { // Use default value of music track
        setVolume(currentMusic, volume);
    }

    public void setVolume(String music) {
        setVolume(music, MUSIC_DEFAULT_VOLUME);
    }

    public void setVolume(String music, float volume) {
        assets.get(music, MUSIC).setVolume(volume);
        resetVariable();
    }

    // Getters + Setter music positions -----------------------------------------------------------
    public float getMusicPosition() {
        return getMusicPosition(currentMusic);
    }

    public float getMusicPosition(String music) {
        return assets.get(music, MUSIC).getPosition();
    }

    public void setMusicPosition(float position) {
        setMusicPosition(currentMusic, position);
    }

    public void setMusicPosition(String music, float position) {
        assets.get(music, MUSIC).setPosition(position);
    }

    // Fading up and down of music
//    public void musicFadeUp(long currentVolume, long desiredVolume, long rate) {
//        for (long i = currentVolume; i <= desiredVolume; i += rate) {
//            setVolume(i);
//        }
//    }
//
//    public void musicFadeDown(long currentVolume, long desiredVolume, long rate) {
//
//    }
}

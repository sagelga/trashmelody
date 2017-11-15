package com.trashmelody.managers;

import com.badlogic.gdx.audio.Music;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import static com.trashmelody.managers.Assets.MUSIC;
import static com.trashmelody.managers.Assets.MUSIC_BG1;

@Singleton
public class MusicManager {
    private Assets assets;
    public Music loadingBackgroundMusic;

    public static float musicVolumeChange = 0.1F;
    public static final float MUSIC_DEFAULT_VOLUME = 0.5F;
    public static String currentBackgroundMusicTrack;

    @Inject
    MusicManager(Assets assets) {
        this.assets = assets;
        this.loadingBackgroundMusic = assets.get(MUSIC_BG1, MUSIC);
    }

    // Set current music track for future use -----------------------------------------------------
    public void setDefault(String music) {
        currentBackgroundMusicTrack = music;
    }

    // Set music states ---------------------------------------------------------------------------
    public void playMusic() {
        playMusic(currentBackgroundMusicTrack);
    }

    public void playMusic(String music) {
        playMusic(music, MUSIC_DEFAULT_VOLUME);
    }

    public void playMusic(float volume) {
        playMusic(currentBackgroundMusicTrack, volume);
    }

    public void playMusic(String music, float volume) {
        assets.get(music, MUSIC).play();
        assets.get(music, MUSIC).setVolume(volume);
    }

    public void pauseMusic(String music) {
        assets.get(music, MUSIC).pause();
    }

    public void stopMusic() {
        assets.get(currentBackgroundMusicTrack, MUSIC).stop();
    }

    public void stopMusic(String music) {
        assets.get(music, MUSIC).stop();
    }

    // Check Background Music status --------------------------------------------------------------
    public boolean getMusicPlaying() {
        return getMusicPlaying(currentBackgroundMusicTrack);
    }

    public boolean getMusicPlaying(String music) {
        return assets.get(music, MUSIC).isPlaying();
    }

    public boolean getMusicLooping(){
        return getMusicLooping(currentBackgroundMusicTrack);
    }

    public boolean getMusicLooping(String music) {
        return assets.get(music, MUSIC).isLooping();
    }

    public void setMusicLooping(){
        setMusicLooping(true);
    }

    public void setMusicLooping(boolean loop){
        setMusicLooping(currentBackgroundMusicTrack, loop);
    }

    public void setMusicLooping(String music, boolean loop){
        assets.get(music,MUSIC).setLooping(loop);
    }

    public String getCurrentBackgroundMusic() {
        return currentBackgroundMusicTrack;
    }

    // Toggling Background Music Volume -----------------------------------------------------------
    public float getBackgroundMusicVolume() {
        return assets.get(currentBackgroundMusicTrack, MUSIC).getVolume();
    }

    public void increaseBackgroundVolume() {
        increaseVolume(currentBackgroundMusicTrack);
    }

    public void increaseVolume(String music) {
        assets.get(music, MUSIC).setVolume(Math.min(assets.get(music, MUSIC).getVolume() + musicVolumeChange, 1F));
    }

    public void decreaseBackgroundVolume() {
        decreaseVolume(currentBackgroundMusicTrack);
    }

    public void decreaseVolume(String music) {
        assets.get(music, MUSIC).setVolume(Math.max(assets.get(music, MUSIC).getVolume() - musicVolumeChange, 0F));
    }

    // Set background Volume ----------------------------------------------------------------------
    public void setVolume() { // Use default value of music track + volume
        setVolume(currentBackgroundMusicTrack);
    }

    public void setVolume(float volume) { // Use default value of music track
        setVolume(currentBackgroundMusicTrack, volume);
    }

    public void setVolume(String music) {
        setVolume(music, MUSIC_DEFAULT_VOLUME);
    }

    public void setVolume(String music, float volume) {
        assets.get(music, MUSIC).setVolume(volume);
    }

    // Getters + Setter music positions -----------------------------------------------------------
    public float getMusicPosition() {
        return getMusicPosition(currentBackgroundMusicTrack);
    }

    public float getMusicPosition(String music) {
        return assets.get(music, MUSIC).getPosition();
    }

    public void setMusicPosition(float position) {
        setMusicPosition(currentBackgroundMusicTrack, position);
    }

    public void setMusicPosition(String music, float position) {
        assets.get(music, MUSIC).setPosition(position);
    }

    // Fading up and down of music
    public void musicFadeUp(long currentVolume, long desiredVolume, long rate) {
        for (long i = currentVolume; i <= desiredVolume; i += rate) {
            setVolume(i);
        }
    }

    public void musicFadeDown(long currentVolume, long desiredVolume, long rate) {

    }
}

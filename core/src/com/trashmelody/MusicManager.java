package com.trashmelody;

import com.badlogic.gdx.audio.Music;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import static com.trashmelody.Assets.MUSIC;
import static com.trashmelody.Assets.MUSIC_BG1;

@Singleton
public class MusicManager {
    private Assets assets;
    public Music loadingBackgroundMusic;

    public static float musicVolumeChange = 0.1F;
    public static final float MUSIC_DEFAULT_VOLUME = 0.5F;
    public static String currentBackgroundMusicTrack;

    @Inject
    public MusicManager(Assets assets) {
        this.assets = assets;
        this.loadingBackgroundMusic = assets.get(MUSIC_BG1, MUSIC);

    }
    public  void setDefault(String music){
        currentBackgroundMusicTrack = music;
        playMusic(music, 0.3F);
    }
    public void playMusic(String music) {
        assets.get(music, MUSIC).play();
        assets.get(music, MUSIC).setVolume(MUSIC_DEFAULT_VOLUME);
    }
    public void playMusic(String music, float volume) {
        assets.get(music, MUSIC).play();
        assets.get(music, MUSIC).setVolume(volume);
    }
    public void pauseMusic(String music){
        assets.get(music, MUSIC).pause();
    }
    public void stopMusic(String music){
        assets.get(music, MUSIC).stop();
    }

    public boolean isMusicPlaying(String music){
        return assets.get(music, MUSIC).isPlaying();
    }
    public boolean isMusicLooping(String music){
        return assets.get(music, MUSIC).isLooping();
    }
    public float getBGMusicVolume(){
        return assets.get(currentBackgroundMusicTrack, MUSIC).getVolume();
    }

    public void increaseBackgroundVolume(String music){
        assets.get(music, MUSIC).setVolume(Math.min(assets.get(music, MUSIC).getVolume() + musicVolumeChange,1F));
    }
    public void increaseBackgroundVolume(){
        assets.get(currentBackgroundMusicTrack, MUSIC).setVolume(Math.min(assets.get(currentBackgroundMusicTrack, MUSIC).getVolume() + musicVolumeChange,1F));
    }
    public void decreaseBackgroundVolume(String music){
        assets.get(music, MUSIC).setVolume(Math.max(assets.get(music, MUSIC).getVolume() - musicVolumeChange,0F));
    }
    public void decreaseBackgroundVolume(){
        assets.get(currentBackgroundMusicTrack, MUSIC).setVolume(Math.max(assets.get(currentBackgroundMusicTrack, MUSIC).getVolume() - musicVolumeChange,0F));
    }

}

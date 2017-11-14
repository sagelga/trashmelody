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

    public  void setDefault(String music){
        currentBackgroundMusicTrack = music;
        playMusic(music, 0.3F);
    }

    public void playMusic(String music) { playMusic(music, MUSIC_DEFAULT_VOLUME); }
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

    // Check Background Music status --------------------------------------------------------------
    public boolean isMusicPlaying(String music){
        return assets.get(music, MUSIC).isPlaying();
    }
    public boolean isMusicLooping(String music){
        return assets.get(music, MUSIC).isLooping();
    }
    public String getCurrentBackgroundMusic() { return currentBackgroundMusicTrack; }

    // Set Background Music Volume ----------------------------------------------------------------
    public float getBackgroundMusicVolume(){
        return assets.get(currentBackgroundMusicTrack, MUSIC).getVolume();
    }

    public void increaseBackgroundVolume(){ increaseVolume(currentBackgroundMusicTrack); }
    public void increaseVolume(String music){
        assets.get(music, MUSIC).setVolume(Math.min(assets.get(music, MUSIC).getVolume() + musicVolumeChange,1F));
    }

    public void decreaseBackgroundVolume(){ decreaseVolume(currentBackgroundMusicTrack); }
    public void decreaseVolume(String music){
        assets.get(music, MUSIC).setVolume(Math.max(assets.get(music, MUSIC).getVolume() - musicVolumeChange,0F));
    }

    public void setBackgroundVolume(){ setVolume(currentBackgroundMusicTrack); }
    public void setBackgroundVolume(float volume){ setVolume(currentBackgroundMusicTrack, volume); }
    public void setVolume(String music){ setVolume(music, MUSIC_DEFAULT_VOLUME);}
    public void setVolume(String music, float volume){
        assets.get(music, MUSIC).setVolume(volume);
    }

    public float getBackgroundPosition(){ return getPosition(currentBackgroundMusicTrack); }
    public float getPosition(String music){
        return assets.get(music, MUSIC).getPosition();
    }

    public void setBackgroundPosition(float position){ setPosition(currentBackgroundMusicTrack, position); }
    public void setPosition(String music, float position){
        assets.get(music, MUSIC).setPosition(position);
    }
}

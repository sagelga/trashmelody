package com.trashmelody;

import com.badlogic.gdx.audio.Music;

import static com.trashmelody.Assets.MUSIC;
import static com.trashmelody.Assets.MUSIC_BG1;

public class MusicManager {
    private Assets assets;
    public Music loadingBackgroundMusic;

    public MusicManager(Assets assets) {
        this.assets = assets;
        this.loadingBackgroundMusic = assets.get(MUSIC_BG1, MUSIC);

    }
    public void playMusic(String music) {
        assets.get(music, MUSIC).play();
    }

}

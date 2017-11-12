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


    @Inject
    public MusicManager(Assets assets) {
        this.assets = assets;
        this.loadingBackgroundMusic = assets.get(MUSIC_BG1, MUSIC);

    }
    public void playMusic(String music) {
        assets.get(music, MUSIC).play();
        assets.get(music, MUSIC).setVolume(MUSIC_DEFAULT_VOLUME);

    }

    public void playMusic(String music, int volume) {
        assets.get(music, MUSIC).play();
        assets.get(music, MUSIC).setVolume(volume);
    }
    public void pauseMusic(String music){
        assets.get(music, MUSIC).pause();
    }
    public void stopMusic(String music){
        assets.get(music, MUSIC).stop();
    }

    public void increaseBackgroundVolume(String music){
        assets.get(music, MUSIC).setVolume(Math.min(assets.get(music, MUSIC).getVolume() + musicVolumeChange,1F));
    }

    public void decreaseBackgroundVolume(String music){
        assets.get(music, MUSIC).setVolume(Math.max(assets.get(music, MUSIC).getVolume() - musicVolumeChange,0F));
    }

}

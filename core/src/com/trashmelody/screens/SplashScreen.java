package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.TrashMelody;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.MusicManager;
import com.trashmelody.managers.ScreenProvider;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.clearScreen;

@Singleton
public class SplashScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private MusicManager musicManager;
    private Camera camera;
    private Stage stage;
    private Texture splashScreenLogo;
    private long timeLapsed;

    @Inject
    SplashScreen(TrashMelody game, ScreenProvider screens, MusicManager musicManager, Camera camera) {
        this.game = game;
        this.screens = screens;
        this.camera = camera;
        this.musicManager = musicManager;
    }

    @Override
    public void show() {
        musicManager.setDefault(MUSIC_BG1);
        musicManager.playMusic();
        musicManager.setMusicLoopStatus(true);
        timeLapsed = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if (TimeUtils.timeSinceMillis(timeLapsed) > 5000) {
            game.setScreen(screens.get(LoadingScreen.class));
        }

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    protected void loadAssets(Assets assets) {
        assets.load(SPLASH_LOGO, TEXTURE);
        assets.load(MUSIC_BG1, MUSIC);
    }

    @Override
    public void afterLoad(Assets assets) {
        splashScreenLogo = assets.get(SPLASH_LOGO, TEXTURE);
        screens.get(WarningScreen.class).load(assets);
        screens.get(MenuScreen.class).load(assets);
        screens.get(SandboxScreen.class).load(assets);
        screens.get(NameScreen.class).load(assets);

        this.stage = getStage();
    }

    private Stage getStage() {
        Image logo = new Image(new TextureRegion(splashScreenLogo));

        Container<Image> container = new Container<>(logo);
        container.setFillParent(true);
        container.center();
        container.maxSize(500 * game.SCALE, 286 * game.SCALE);

        Stage stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);
        stage.addActor(container);

        return stage;
    }
}

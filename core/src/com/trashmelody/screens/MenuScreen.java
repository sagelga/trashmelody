package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.*;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.MusicManager;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class MenuScreen extends LazyScreen {
    private TrashMelody game;
    private StageSelectScreen stageSelectScreen;
    private ResultScreen resultScreen;
    private MusicManager musicManager;

    private Camera camera;
    private Viewport viewport;
    private Texture splashScreenLogo;
    private Texture bg, btnStart, btnCollection, btnSetting, btnExit, borderLeft, borderRight;

    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

    @Inject
    MenuScreen(TrashMelody game, Camera camera, Viewport viewport, ScreenProvider screens, MusicManager musicManager) {
        this.game = game;
        this.stageSelectScreen = screens.get(StageSelectScreen.class);
        this.resultScreen = screens.get(ResultScreen.class);
        this.musicManager = musicManager;
        this.camera = camera;
        this.viewport = viewport;
    }

    @Override
    public void show() {
        if (!musicManager.isMusicPlaying(MUSIC_BG1)) {
            musicManager.playMusic(MUSIC_BG1);
        }
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();

        game.batch.begin();
        drawCenterX(game.batch, bg, 691 * 2F, vh, 0);
        drawCenterX(game.batch, splashScreenLogo, 320F, 183F, 350F);
        drawCenterX(game.batch, btnStart, 320F, 56F, 400F);
        drawCenterX(game.batch, btnCollection, 320F, 56F, 300F);
        drawCenterX(game.batch, btnSetting, 320F, 56F, 200F);
        drawCenterX(game.batch, btnExit, 320F, 56F, 100F);
        game.batch.draw(borderLeft, 0, 0, ((float) 168 / 900) * vh, vh);
        game.batch.draw(borderRight, vw - (((float) 168 / 900) * vh), 0, ((float) 168 / 900) * vh, 900);

        // Click 'ENTER' equivalent to clicking play (for now)

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setLazyScreen(stageSelectScreen);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            game.setLazyScreen(resultScreen);
        }
        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Main Menu Screen");
        // Debug zone

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }

    @Override
    public void hide() {
        musicManager.stopMusic(MUSIC_BG1);
    }

    @Override
    protected void loadAssets(Assets assets) {
        assets.load(SPLASH_LOGO, TEXTURE);
        assets.load(MENU_BG, TEXTURE);
        assets.load(MENU_BTN_START, TEXTURE);
        assets.load(MENU_BTN_COLLECTION, TEXTURE);
        assets.load(MENU_BTN_SETTING, TEXTURE);
        assets.load(MENU_BTN_EXIT, TEXTURE);
        assets.load(MENU_BORDER_LEFT, TEXTURE);
        assets.load(MENU_BORDER_RIGHT, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.splashScreenLogo = assets.get(SPLASH_LOGO, TEXTURE);
        this.bg = assets.get(MENU_BG, TEXTURE);
        this.btnStart = assets.get(MENU_BTN_START, TEXTURE);
        this.btnCollection = assets.get(MENU_BTN_COLLECTION, TEXTURE);
        this.btnSetting = assets.get(MENU_BTN_SETTING, TEXTURE);
        this.btnExit = assets.get(MENU_BTN_EXIT, TEXTURE);
        this.borderLeft = assets.get(MENU_BORDER_LEFT, TEXTURE);
        this.borderRight = assets.get(MENU_BORDER_RIGHT, TEXTURE);
    }
}

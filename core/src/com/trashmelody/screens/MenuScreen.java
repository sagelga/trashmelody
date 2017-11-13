package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private Stage stage = new Stage();

    private int menuCount = 1;

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
        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) menuCount++;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) menuCount--;

        drawCenterX(game.batch, bg, 691 * 2F, vh, 0);
        drawCenterX(game.batch, splashScreenLogo, 450, findRatio(320, 183, 450, 'h'), vh-(findRatio(320, 183, 450, 'h')+100));

        if (menuCount != 1) drawCenterX(game.batch, btnStart, 320F, 56F, 400F);
        else drawCenterX(game.batch, btnCollection, 320F, 56F, 400F);

        if (menuCount != 2) drawCenterX(game.batch, btnCollection, 320F, 56F, 300F);
        else drawCenterX(game.batch, btnCollection, 320F, 56F, 300F);

        if (menuCount != 3) drawCenterX(game.batch, btnSetting, 320F, 56F, 200F);
        else drawCenterX(game.batch, btnCollection, 320F, 56F, 200F);

        if (menuCount != 4) drawCenterX(game.batch, btnExit, 320F, 56F, 100F);
        else drawCenterX(game.batch, btnCollection, 320F, 56F, 100F);

        if (menuCount > 4) menuCount = 1; else if (menuCount < 1) menuCount = 4;

        game.batch.draw(borderLeft, 0, 0, findRatio(168, 900, vh, 'w'), vh);
        game.batch.draw(borderRight, vw - findRatio(168, 900, vh, 'w'), 0, findRatio(168, 900, vh, 'w'), vh);

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

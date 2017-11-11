package com.trashmelody.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;


import com.google.inject.Provider;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;
import static com.trashmelody.Utils.*;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;


import javax.inject.Inject;

public class GameScreen extends ScreenAdapter {
    private TrashMelody game;
    private Camera camera;
    private Viewport viewport;
    private Stage stage;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();


    // Defining building value
    private Texture bg1;
    private Texture redBinPlot;
    private Texture levelCover;
    private Texture bgFooter;
    private Texture recycleBinPlot;
    private Texture pauseTab;
    private Texture dangerRedBin;
    private Texture yellowBinPlot;
    private Texture scoreTitle;
    private Texture recycleBin;
    private Texture idkBinPlot;
    private Texture miss;
    private Texture yellowBin;
    private Texture songNameBar;
    private Texture bad;
    private Texture idkBin;
    private Texture levelScoreBar;
    private Texture cool;
    private Texture rainbowFlashBin;
    private Texture hpPoint;
    private Texture good;
    private Texture centerLine;
    private Texture normal;
    private Texture perfect;
    private Texture check;
    private Texture easy;
    private Texture songName;
    private Texture footerTab;
    private Texture hard;
    private Texture hpBar;

    @Inject
    public GameScreen(TrashMelody game, Assets assets, Camera camera, Viewport viewport) {
        this.game = game;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);


        //Variable handlers     |Assets retrival path
        this.bg1 = assets.get(Assets.GAME_BACKGROUND1, Assets.TEXTURE);
        this.bgFooter = assets.get(Assets.GAME_BACKGROUND_FOOTER1, Assets.TEXTURE);
        this.dangerRedBin = assets.get(Assets.GAME_BIN_01, Assets.TEXTURE);
        this.recycleBin = assets.get(Assets.GAME_BIN_02, Assets.TEXTURE);
        this.yellowBin = assets.get(Assets.GAME_BIN_03, Assets.TEXTURE);
        this.idkBin = assets.get(Assets.GAME_BIN_04, Assets.TEXTURE);
        this.rainbowFlashBin = assets.get(Assets.GAME_BIN_05, Assets.TEXTURE);
        this.centerLine = assets.get(Assets.GAME_CENTER, Assets.TEXTURE);
        this.check = assets.get(Assets.GAME_CHECK, Assets.TEXTURE);
        this.footerTab = assets.get(Assets.GAME_FOOTER, Assets.TEXTURE);
        this.redBinPlot = assets.get(Assets.GAME_HANOI_1, Assets.TEXTURE);
        this.recycleBinPlot = assets.get(Assets.GAME_HANOI_2, Assets.TEXTURE);
        this.yellowBinPlot = assets.get(Assets.GAME_HANOI_3, Assets.TEXTURE);
        this.idkBinPlot = assets.get(Assets.GAME_HANOI_4, Assets.TEXTURE);
        this.songNameBar = assets.get(Assets.GAME_HEADER, Assets.TEXTURE);
        this.levelScoreBar = assets.get(Assets.GAME_HEADER2, Assets.TEXTURE);
        this.hpPoint = assets.get(Assets.GAME_ICON, Assets.TEXTURE);
        this.normal = assets.get(Assets.GAME_LEVEL_1, Assets.TEXTURE);
        this.easy = assets.get(Assets.GAME_LEVEL_2, Assets.TEXTURE);
        this.hard = assets.get(Assets.GAME_LEVEL_3, Assets.TEXTURE);
        this.levelCover = assets.get(Assets.GAME_LEVEL_BORDER, Assets.TEXTURE);
        this.pauseTab = assets.get(Assets.GAME_PAUSE, Assets.TEXTURE);
        this.scoreTitle = assets.get(Assets.GAME_SCORE, Assets.TEXTURE);
        this.miss = assets.get(Assets.GAME_SCORE_1, Assets.TEXTURE);
        this.bad = assets.get(Assets.GAME_SCORE_2, Assets.TEXTURE);
        this.cool = assets.get(Assets.GAME_SCORE_3, Assets.TEXTURE);
        this.good = assets.get(Assets.GAME_SCORE_4, Assets.TEXTURE);
        this.perfect = assets.get(Assets.GAME_SCORE_5, Assets.TEXTURE);
        this.songName = assets.get(Assets.GAME_SONG_NAME_1, Assets.TEXTURE);
        this.hpBar = assets.get(Assets.GAME_STATUS_BAR, Assets.TEXTURE);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        //Components Draw
        game.batch.draw(bg1,0,vh/10,vw,vh/1.15F);
        game.batch.draw(bgFooter,0,0,vw,vh/2);
        game.batch.draw(levelScoreBar,vw/2,vh/1.07F,vw/2,vh/7);
        game.batch.draw(songNameBar,0,vh/1.09F,vw/1.8F,vh/10);
        game.batch.draw(songName,vw/128,vh/1.05F,vw/3.2F,vh/32);
        game.batch.draw(dangerRedBin,vw/128,vw/128,vw/7F,vh/5);
        game.batch.draw(recycleBin,vw/5.6F,vw/128,vw/7F,vh/5);
        game.batch.draw(yellowBin,vw/1.47F,vw/128,vw/7F,vh/5);
        game.batch.draw(idkBin,vw/1.18F,vw/128,vw/7F,vh/5);
        game.batch.draw(redBinPlot,vw/128,vh/2.3F,vw/7F,vh/16);
        game.batch.draw(recycleBinPlot,vw/5.6F,vh/2.3F,vw/7F,vh/16);
        game.batch.draw(yellowBinPlot,vw/1.47F,vh/2.3F,vw/7F,vh/16);
        game.batch.draw(idkBinPlot,vw/1.18F,vh/2.3F,vw/7F,vh/16);
        game.batch.draw(footerTab,0,0,vw,vh/12);
        game.batch.draw(levelCover,vw/1.8F,vh/1.05F,vw/7,vh/30);
        game.batch.draw(pauseTab,vw/1.16F,vh/100,vw/8,vh/24);
        game.batch.draw(normal,vw/1.73F,vh/1.05F,vw/10,vh/30);
        //game.batch.draw(easy,vw/1.73F,vh/1.05F,vw/10,vh/30);
        //game.batch.draw(hard,vw/1.73F,vh/1.05F,vw/10,vh/30);
        game.batch.draw(scoreTitle,vw/1.39F,vh/1.05F,vw/10,vh/30);
        game.batch.draw(hpBar,vw/3,vh/20,vw/3,vh/30);
        game.batch.draw(hpPoint,vw/1.56F,vh/20.2F,vw/40,vh/24);
        //game.batch.draw(miss,vw/8,vh/1.8F,vw/5,vh/3);
        //game.batch.draw(bad,vw/8,vh/1.8F,vw/5,vh/3);
        game.batch.draw(cool,vw/8,vh/1.8F,vw/5,vh/3);
        //game.batch.draw(good,vw/8,vh/1.8F,vw/5,vh/3);
        //game.batch.draw(perfect,vw/8,vh/1.8F,vw/5,vh/3);
        game.batch.draw(check,vw/32,vh/2,vw/5,vh/2.4F);
        game.batch.draw(centerLine,0,vh/2.02F,vw,vh/128);

        // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) Debugger.debug_mode = !Debugger.debug_mode;
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Game Screen");
        // Debug zone

        game.batch.end();

    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
    }

}
package com.trashmelody.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.TrashMelody;
import com.trashmelody.components.*;
import com.trashmelody.entities.Dispatcher;
import com.trashmelody.entities.Platform;
import com.trashmelody.entities.Player;
import com.trashmelody.entities.ScanLine;
import com.trashmelody.handlers.KeyboardController;
import com.trashmelody.managers.Assets;
import com.trashmelody.utils.Debugger;
import lt.ekgame.beatmap_analyzer.beatmap.Beatmap;
import lt.ekgame.beatmap_analyzer.beatmap.mania.ManiaBeatmap;
import lt.ekgame.beatmap_analyzer.parser.BeatmapException;
import lt.ekgame.beatmap_analyzer.parser.BeatmapParser;

import java.io.File;
import java.io.FileNotFoundException;

import static com.badlogic.gdx.Input.Keys.*;
import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class GameScreen extends LazyScreen {
    private TrashMelody game;
    private Assets assets;
    private Camera camera;
    private Viewport viewport;
    private InputProcessor inputProcessor;
    private Engine engine;
    private World world;
    private Beatmap beatmap;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();

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
    private Texture yellowBin;
    private Texture songNameBar;
    private Texture perfect;
    private Texture good;
    private Texture cool;
    private Texture bad;
    private Texture miss;
    private Texture idkBin;
    private Texture levelScoreBar;
    private Texture rainbowFlashBin;
    private Texture hpPoint;
    private Texture centerLine;
    private Texture normal;
    private Texture check;
    private Texture easy;
    private Texture songName;
    private Texture footerTab;
    private Texture hard;
    private Texture hpBar;

    @Inject
    GameScreen(TrashMelody game,
               Engine engine,
               World world,
               Assets assets,
               Camera camera,
               Viewport viewport,
               KeyboardController inputProcessor) {
        this.game = game;
        this.camera = camera;
        this.engine = engine;
        this.world = world;
        this.assets = assets;
        this.inputProcessor = inputProcessor;
        this.beatmap = getBeatmap();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        clearScreen(0, 0, 0, 1);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        drawBackground();
        game.batch.end();

        if (isLoaded()) {
            engine.update(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
//        viewport.update(width, height);
    }

    @Override
    public void loadAssets(Assets assets) {
        assets.load(GAME_BACKGROUND1, TEXTURE);
        assets.load(GAME_BACKGROUND_FOOTER1, TEXTURE);
        assets.load(GAME_BIN_01, TEXTURE);
        assets.load(GAME_BIN_02, TEXTURE);
        assets.load(GAME_BIN_03, TEXTURE);
        assets.load(GAME_BIN_04, TEXTURE);
        assets.load(GAME_BIN_05, TEXTURE);
        assets.load(GAME_CENTER, TEXTURE);
        assets.load(GAME_CHECK, TEXTURE);
        assets.load(GAME_FOOTER, TEXTURE);
        assets.load(GAME_HANOI_1, TEXTURE);
        assets.load(GAME_HANOI_2, TEXTURE);
        assets.load(GAME_HANOI_3, TEXTURE);
        assets.load(GAME_HANOI_4, TEXTURE);
        assets.load(GAME_HEADER, TEXTURE);
        assets.load(GAME_HEADER2, TEXTURE);
        assets.load(GAME_ICON, TEXTURE);
        assets.load(GAME_LEVEL_1, TEXTURE);
        assets.load(GAME_LEVEL_2, TEXTURE);
        assets.load(GAME_LEVEL_3, TEXTURE);
        assets.load(GAME_LEVEL_BORDER, TEXTURE);
        assets.load(GAME_PAUSE, TEXTURE);
        assets.load(GAME_SCORE, TEXTURE);
        assets.load(MISS_ACCURACY, TEXTURE);
        assets.load(BAD_ACCURACY, TEXTURE);
        assets.load(COOL_ACCURACY, TEXTURE);
        assets.load(GOOD_ACCURACY, TEXTURE);
        assets.load(PERFECT_ACCURACY, TEXTURE);
        assets.load(GAME_SONG_NAME_1, TEXTURE);
        assets.load(GAME_STATUS_BAR, TEXTURE);
        assets.load(CIGARETTE_HIT_OBJECT, TEXTURE);
        assets.load(HAIR_SPRAY_HIT_OBJECT, TEXTURE);
        assets.load(OIL_CAN_HIT_OBJECT, TEXTURE);
        assets.load(PLASTIC_BAG_HIT_OBJECT, TEXTURE);
        assets.load(THINNER_HIT_OBJECT, TEXTURE);
        assets.load(MUSIC_1_SONG, MUSIC);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bg1 = assets.get(GAME_BACKGROUND1, TEXTURE);
        this.bgFooter = assets.get(GAME_BACKGROUND_FOOTER1, TEXTURE);
        this.dangerRedBin = assets.get(GAME_BIN_01, TEXTURE);
        this.recycleBin = assets.get(GAME_BIN_02, TEXTURE);
        this.yellowBin = assets.get(GAME_BIN_03, TEXTURE);
        this.idkBin = assets.get(GAME_BIN_04, TEXTURE);
        this.rainbowFlashBin = assets.get(GAME_BIN_05, TEXTURE);
        this.centerLine = assets.get(GAME_CENTER, TEXTURE);
        this.check = assets.get(GAME_CHECK, TEXTURE);
        this.footerTab = assets.get(GAME_FOOTER, TEXTURE);
        this.redBinPlot = assets.get(GAME_HANOI_1, TEXTURE);
        this.recycleBinPlot = assets.get(GAME_HANOI_2, TEXTURE);
        this.yellowBinPlot = assets.get(GAME_HANOI_3, TEXTURE);
        this.idkBinPlot = assets.get(GAME_HANOI_4, TEXTURE);
        this.songNameBar = assets.get(GAME_HEADER, TEXTURE);
        this.levelScoreBar = assets.get(GAME_HEADER2, TEXTURE);
        this.hpPoint = assets.get(GAME_ICON, TEXTURE);
        this.normal = assets.get(GAME_LEVEL_1, TEXTURE);
        this.easy = assets.get(GAME_LEVEL_2, TEXTURE);
        this.hard = assets.get(GAME_LEVEL_3, TEXTURE);
        this.levelCover = assets.get(GAME_LEVEL_BORDER, TEXTURE);
        this.pauseTab = assets.get(GAME_PAUSE, TEXTURE);
        this.scoreTitle = assets.get(GAME_SCORE, TEXTURE);
        this.miss = assets.get(MISS_ACCURACY, TEXTURE);
        this.bad = assets.get(BAD_ACCURACY, TEXTURE);
        this.cool = assets.get(COOL_ACCURACY, TEXTURE);
        this.good = assets.get(GOOD_ACCURACY, TEXTURE);
        this.perfect = assets.get(PERFECT_ACCURACY, TEXTURE);
        this.songName = assets.get(GAME_SONG_NAME_1, TEXTURE);
        this.hpBar = assets.get(GAME_STATUS_BAR, TEXTURE);

        createEntities();
    }

    private void createEntities() {
        engine.addEntity(new Platform(world));
        engine.addEntity(new Player(
                world,
                new PlayerComponent(D, F, J, K),
                new TypeComponent(TypeComponent.PLAYER)
        ));
        engine.addEntity(new ScanLine(
                world,
                new ScanLineComponent(assets.get(MUSIC_1_SONG, MUSIC), 3F),
                new TextureComponent(check)
        ));
        engine.addEntity(new Dispatcher(
                world,
                new DispatchComponent(beatmap, 3F)
        ));
    }

    private void drawBackground() {
        game.batch.draw(bg1, 0, vh / 10, vw, vh / 1.15F);
        game.batch.draw(bgFooter, 0, 0, vw, vh / 2);
        game.batch.draw(levelScoreBar, vw / 2, vh / 1.07F, vw / 2, vh / 7);
        game.batch.draw(songNameBar, 0, vh / 1.09F, vw / 1.8F, vh / 10);
        game.batch.draw(songName, vw / 128, vh / 1.05F, vw / 3.2F, vh / 32);
        game.batch.draw(dangerRedBin, vw / 128, vw / 128, vw / 7F, vh / 5);
        game.batch.draw(recycleBin, vw / 5.6F, vw / 128, vw / 7F, vh / 5);
        game.batch.draw(yellowBin, vw / 1.47F, vw / 128, vw / 7F, vh / 5);
        game.batch.draw(idkBin, vw / 1.18F, vw / 128, vw / 7F, vh / 5);
        game.batch.draw(redBinPlot, vw / 128, vh / 2.3F, vw / 7F, vh / 16);
        game.batch.draw(recycleBinPlot, vw / 5.6F, vh / 2.3F, vw / 7F, vh / 16);
        game.batch.draw(yellowBinPlot, vw / 1.47F, vh / 2.3F, vw / 7F, vh / 16);
        game.batch.draw(idkBinPlot, vw / 1.18F, vh / 2.3F, vw / 7F, vh / 16);
        game.batch.draw(footerTab, 0, 0, vw, vh / 12);
        game.batch.draw(levelCover, vw / 1.8F, vh / 1.05F, vw / 7, vh / 30);
        game.batch.draw(pauseTab, vw / 1.16F, vh / 100, vw / 8, vh / 24);
        game.batch.draw(normal, vw / 1.73F, vh / 1.05F, vw / 10, vh / 30);
        //game.batch.draw(easy,vw/1.73F,vh/1.05F,vw/10,vh/30);
        //game.batch.draw(hard,vw/1.73F,vh/1.05F,vw/10,vh/30);
        game.batch.draw(scoreTitle, vw / 1.39F, vh / 1.05F, vw / 10, vh / 30);
        game.batch.draw(hpBar, vw / 3, vh / 20, vw / 3, vh / 30);
        game.batch.draw(hpPoint, vw / setHpBar(50), vh / 20.2F, vw / 40, vh / 24);
        //game.batch.draw(miss,vw/8,vh/1.8F,vw/5,vh/3);
        //game.batch.draw(bad,vw/8,vh/1.8F,vw/5,vh/3);
        game.batch.draw(cool, vw / 8, vh / 1.8F, vw / 5, vh / 3);
        //game.batch.draw(good,vw/8,vh/1.8F,vw/5,vh/3);
        //game.batch.draw(perfect,vw/8,vh/1.8F,vw/5,vh/3);
        game.batch.draw(check, vw / 32, vh / 2, vw / 5, vh / 2.4F);
        game.batch.draw(centerLine, 0, vh / 2.02F, vw, vh / 128);

        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Game Screen");
    }

    private Beatmap getBeatmap() {
        BeatmapParser parser = new BeatmapParser();
        File file = new File(HITORIGOTO_HARD);
        Beatmap beatmap = null;
        try {
            beatmap = parser.parse(file, ManiaBeatmap.class);
        } catch (BeatmapException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return beatmap;
    }

    private float setHpBar(int hp) {
        if (hp >= 0 && hp <= 100) return (float)(3F - hp / 100F * (3F - 1.56F));
        return 0;
    }
}

package com.trashmelody.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.TrashMelody;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.components.*;
import com.trashmelody.constants.Constants;
import com.trashmelody.entities.Dispatcher;
import com.trashmelody.entities.Platform;
import com.trashmelody.entities.Player;
import com.trashmelody.entities.ScanLine;
import com.trashmelody.handlers.KeyboardController;
import com.trashmelody.managers.Assets;
import com.trashmelody.systems.Systems;
import com.trashmelody.utils.Debugger;

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
    private ScanLineComponent scanLine;
    private HealthComponent health;
    private DispatchComponent dispatch;
    private BitmapFont font;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private Beatmap beatmap;
    private Music music;

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
    private SpriteBatch batch;

    @Inject
    GameScreen(TrashMelody game,
               Engine engine,
               World world,
               Assets assets,
               Camera camera,
               Viewport viewport,
               KeyboardController inputProcessor,
               SpriteBatch batch) {
        this.game = game;
        this.camera = camera;
        this.engine = engine;
        this.world = world;
        this.assets = assets;
        this.inputProcessor = inputProcessor;
//        this.beatmap = getBeatmap();
        this.batch = batch;
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
        this.font = assets.getSuperSpaceFont(40, Color.WHITE);

        createEntities();
    }

    public void restartGame() {
        engine.removeAllEntities();
        game.injector.getInstance(Systems.class).list.stream()
            .map(game.injector::getInstance)
            .forEach(engine::addSystem);
        createEntities();
        setLoaded(true);
    }

    public void stop() {
        setLoaded(false);
    }

    private void createEntities() {
        scanLine = new ScanLineComponent(music, 2F);
        health = new HealthComponent(Constants.MAX_HEALTH);
//        dispatch = new DispatchComponent(beatmap, 2F);
        engine.addEntity(new Platform(world));
        engine.addEntity(new Player(
            world,
            new PlayerComponent(D, F, J, K),
            new TypeComponent(TypeComponent.PLAYER)
        ));
        engine.addEntity(new ScanLine(
            world,
            scanLine,
            new TextureComponent(check),
            health
        ));
        engine.addEntity(new Dispatcher(
            world,
            dispatch
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
        game.batch.draw(hpPoint, vw / getHpSliderPositionX(health), vh / 20.2F, vw / 40, vh / 24);
        //game.batch.draw(miss,vw/8,vh/1.8F,vw/5,vh/3);
        //game.batch.draw(bad,vw/8,vh/1.8F,vw/5,vh/3);
        game.batch.draw(cool, vw / 8, vh / 1.8F, vw / 5, vh / 3);
        //game.batch.draw(good,vw/8,vh/1.8F,vw/5,vh/3);
        //game.batch.draw(perfect,vw/8,vh/1.8F,vw/5,vh/3);
        game.batch.draw(check, vw / 32, vh / 2, vw / 5, vh / 2.4F);
        game.batch.draw(centerLine, 0, vh / 2.02F, vw, vh / 128);
        font.draw(batch,Integer.toString(scanLine.score.totalScore),vw/1.2F,vh/1.016F);

        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Game Screen");
    }

    private float getHpSliderPositionX(HealthComponent health) {
        float healthPercentage = health.health / health.getMaxHealth() * 100;
        return Math.min(3F - healthPercentage / 100F * (3F - 1.56F), 3F);
    }

    public void setBeatmap(Beatmap beatmap) {
        this.beatmap = beatmap;
        this.dispatch = new DispatchComponent(beatmap, 2F);
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}

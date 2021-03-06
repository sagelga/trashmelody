package com.trashmelody.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
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
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.managers.TrashManager;
import com.trashmelody.models.Score;
import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;
import com.trashmelody.systems.Systems;
import com.trashmelody.utils.Debugger;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

import java.util.Vector;

import static com.badlogic.gdx.Input.Keys.*;
import static com.trashmelody.constants.B2Dvars.PPM;
import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class GameScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private TrashManager trashManager;
    private OrthographicCamera camera;
    private InputProcessor inputProcessor;
    private Engine engine;
    private World world;
    private ScanLineComponent scanLine;
    private HealthComponent health;
    private DispatchComponent dispatch;
    private BitmapFont font, font_SongTitle;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private Beatmap beatmap;
    private Music music;
    private int counter = 0;

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
    private Command command = Command.Loading;

    public enum Command {
        Loading, Ready, Waiting, Start, Restart, End
    }

    @Inject
    GameScreen(TrashMelody game,
               Engine engine,
               World world,
               ScreenProvider screens,
               TrashManager trashManager,
               OrthographicCamera camera,
               KeyboardController inputProcessor,
               SpriteBatch batch) {
        this.game = game;
        this.engine = engine;
        this.world = world;
        this.screens = screens;
        this.camera = camera;
        this.inputProcessor = inputProcessor;
        this.batch = batch;
        this.trashManager = trashManager;
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

        handleCommand(delta);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        drawForeground();
        game.batch.end();
    }

    private void handleCommand(float delta) {
        switch (command) {
            case Loading:
                if (isLoaded()) {
                    command = Command.Ready;
                }
                break;
            case Ready:
                counter = 3;
                command = Command.Waiting;
                break;
            case Waiting:
                if (counter > 0) {
                    counter -= delta;
                } else {
                    command = Command.Start;
                }
                break;
            case Start:
                engine.update(delta);
                break;
            case Restart:
                scanLine.music.stop();
                restartGame();
                command = Command.Ready;
                break;
            case End:
                Score score = scanLine.score;
                ResultScreen resultScreen = screens.get(ResultScreen.class);
                resultScreen.setScores(
                    score.perfect,
                    score.good,
                    score.nice,
                    score.miss + score.bad,
                    17,
                    score.totalScore,
                    beatmap.getBeatmapId()
                );
                resultScreen.setGrade(score.totalScore, score.totalScore);
                game.setLazyScreen(resultScreen);
                break;
        }
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    private void restartGame() {
        clearEntities();
        prepareEngine();
    }

    private void prepareEngine() {
        createEntities();
        game.injector.getInstance(Systems.class).list.stream()
            .map(game.injector::getInstance)
            .forEach(engine::addSystem);
    }

    private void clearEntities() {
        engine.getEntitiesFor(Family.all(PhysicsComponent.class).get())
            .forEach(entity -> world.destroyBody(Mapper.physics.get(entity).body));
        engine.removeAllEntities();
    }

    private void createEntities() {
        scanLine = new ScanLineComponent(music, beatmap, 2F);
        health = new HealthComponent(Constants.MAX_HEALTH);
        dispatch = new DispatchComponent(beatmap, 2F);
        engine.addEntity(new Player(
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

    private float getHpSliderPositionX(HealthComponent health) {
        float healthPercentage = health.health / health.getMaxHealth() * 100;
        return Math.max(Math.min(3F - healthPercentage / 100F * (3F - 1.56F), 3F), 1.56F);
    }

    public void setBeatmap(Beatmap beatmap) {
        this.beatmap = beatmap;
        this.dispatch = new DispatchComponent(beatmap, 2F);
    }

    public void setMusic(Music music) {
        this.music = music;
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
        assets.load(GLOBAL_FOOTER_METAL_BAR, TEXTURE);
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
        assets.load(GLOBAL_ICON_PAUSE, TEXTURE);
        assets.load(GAME_SCORE, TEXTURE);
        assets.load(MISS_ACCURACY, TEXTURE);
        assets.load(BAD_ACCURACY, TEXTURE);
        assets.load(NICE_ACCURACY, TEXTURE);
        assets.load(GOOD_ACCURACY, TEXTURE);
        assets.load(PERFECT_ACCURACY, TEXTURE);
//        assets.load(GAME_SONG_NAME_1, TEXTURE);
        assets.load(GAME_STATUS_BAR, TEXTURE);
        assets.load(MUSIC_1_SONG, MUSIC);

        trashManager.getTrashes()
            .map(Trash::getTexturePath)
            .forEach(texture -> assets.load(texture, TEXTURE));

        trashManager.getTrashes()
            .map(Trash::getRawTexturePath)
            .forEach(texture -> assets.load(texture, TEXTURE));

        screens.get(PauseScreen.class).loadAssets(assets);
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
        this.footerTab = assets.get(GLOBAL_FOOTER_METAL_BAR, TEXTURE);
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
        this.pauseTab = assets.get(GLOBAL_ICON_PAUSE, TEXTURE);
        this.scoreTitle = assets.get(GAME_SCORE, TEXTURE);
        this.miss = assets.get(MISS_ACCURACY, TEXTURE);
        this.bad = assets.get(BAD_ACCURACY, TEXTURE);
        this.cool = assets.get(NICE_ACCURACY, TEXTURE);
        this.good = assets.get(GOOD_ACCURACY, TEXTURE);
        this.perfect = assets.get(PERFECT_ACCURACY, TEXTURE);
//        this.songName = assets.get(GAME_SONG_NAME_1, TEXTURE);
        this.hpBar = assets.get(GAME_STATUS_BAR, TEXTURE);
        this.font = assets.getSuperSpaceFont(40, Color.WHITE);
        this.font_SongTitle = assets.getSuperSpaceFont(60, Color.WHITE);

        prepareEngine();
    }

    private void drawBackground() {
        String songTitleToShow = beatmap.getMetadata().getTitleRomanized();

        game.batch.draw(bg1, 0, vh / 10, vw, vh / 1.15F);
        game.batch.draw(bgFooter, 0, 0, vw, vh / 2);
        game.batch.draw(levelScoreBar, vw / 2, vh / 1.07F, vw / 2, vh / 7);
        game.batch.draw(songNameBar, 0, vh / 1.09F, vw / 1.8F, vh / 10);
        font_SongTitle.draw(batch, songTitleToShow, vw / 64, vh / 1.02F);
        game.batch.draw(levelCover, vw / 1.8F, vh / 1.05F, vw / 7, vh / 30);
        game.batch.draw(normal, vw / 1.73F, vh / 1.05F, vw / 10, vh / 30);
        //game.batch.draw(easy,vw/1.73F,vh/1.05F,vw/10,vh/30);
        //game.batch.draw(hard,vw/1.73F,vh/1.05F,vw/10,vh/30);
        game.batch.draw(scoreTitle, vw / 1.39F, vh / 1.05F, vw / 10, vh / 30);
        game.batch.draw(hpBar, vw / 3, vh / 20, vw / 3, vh / 30);
        game.batch.draw(hpPoint, vw / getHpSliderPositionX(health), vh / 20.2F, vw / 40, vh / 24);
        game.batch.draw(centerLine, 0, vh / 2.02F, vw, vh / 128);
        font.draw(batch, Integer.toString(scanLine.score.totalScore), vw / 1.2F, vh / 1.016F);

//        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Game Screen");
    }

    private void drawForeground() {
        game.batch.draw(dangerRedBin, vw / 128, vw / 128, vw / 7F, vh / 5);
        game.batch.draw(recycleBin, vw / 5.6F, vw / 128, vw / 7F, vh / 5);
        game.batch.draw(yellowBin, vw / 1.47F, vw / 128, vw / 7F, vh / 5);
        game.batch.draw(idkBin, vw / 1.18F, vw / 128, vw / 7F, vh / 5);
        game.batch.draw(redBinPlot, vw / 128, vh / 2.3F, vw / 7F, vh / 16);
        game.batch.draw(recycleBinPlot, vw / 5.6F, vh / 2.3F, vw / 7F, vh / 16);
        game.batch.draw(yellowBinPlot, vw / 1.47F, vh / 2.3F, vw / 7F, vh / 16);
        game.batch.draw(idkBinPlot, vw / 1.18F, vh / 2.3F, vw / 7F, vh / 16);
        game.batch.draw(footerTab, 0, 0, vw, findRatio(1920, 80, vw, 'h'));
        game.batch.draw(pauseTab, vw / 1.16F, 0, vw / 8, findRatio(218, 59, vw/8, 'h'));
    }

    public static Map<TrashType, Vector2> binPositionMapper = HashMap.of(
        TrashType.Dangerous, new Vector2(160 / PPM, 500 / PPM),
        TrashType.Recycle, new Vector2(480 / PPM, 500 / PPM),
        TrashType.Wet, new Vector2(1440 / PPM, 500 / PPM),
        TrashType.General, new Vector2(1760 / PPM, 500 / PPM)
    );
}

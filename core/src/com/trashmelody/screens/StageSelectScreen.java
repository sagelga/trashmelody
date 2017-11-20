package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.*;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.constants.BeatmapGroupId;
import com.trashmelody.managers.*;
import com.trashmelody.models.Building;
import com.trashmelody.utils.Debugger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;

import java.nio.file.*;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class StageSelectScreen extends LazyScreen {
    private static final int MUSIC_PREVIEW_DELAY = 0;

    private TrashMelody game;
    private ScreenProvider screens;
    private OrthographicCamera camera;
    private MusicManager musicManager;
    private Assets assets;
    private Viewport viewport;
    private GameScreen gameScreen;
    private SpriteBatch batch;
    private BeatmapManager beatmapManager;
    private StatsManager statsManager;
    private Map<String, Stream<Beatmap>> beatmaps;
    private Beatmap currentBeatmap = null;

    private Building cafe = new Building("Cafe", BeatmapGroupId.HITORIGOTO_BEATMAP_GROUP_ID);
    private Building cinema = new Building("Cinema", BeatmapGroupId.MARBLE_SODA_BEATMAP_GROUP_ID);
    private Building hospital = new Building("Hospital", BeatmapGroupId.REUNION_BEATMAP_GROUP_ID);
    private Building school = new Building("School", BeatmapGroupId.KANASHII_URESHII_BEATMAP_GROUP_ID);
    private Building home = new Building("Home", BeatmapGroupId.MIRROR_BEATMAP_GROUP_ID);
    private Building office = new Building("Office", BeatmapGroupId.STEP_AHEAD_BEATMAP_GROUP_ID);

    // Defining building value
    private Texture bdHomeShow, bdCafeShow, bdCinemaShow, bdHospitalShow, bdSchoolShow, bdOfficeShow;
    private Texture bdHomeHide, bdCafeHide, bdCinemaHide, bdHospitalHide, bdSchoolHide, bdOfficeHide;
    private Texture buttonBack, buttonContinue, header, footer, cloud, trashworldLogo, selectArrowLeft, selectArrowRight, bg;

    private BitmapFont font_HighScore, font_StageName;

    private Music music1, music2, music3, music4, music5, music6;
    private Music SFxStageChange;

    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private int currentStageNumber = 0;
    private int modes, cooldown;
    int times = 1;

    @Inject
    StageSelectScreen(TrashMelody game,
                      OrthographicCamera camera,
                      ScreenProvider screens,
                      MusicManager musicManager,
                      StatsManager statsManager,
                      SpriteBatch batch,
                      Viewport viewport,
                      BeatmapManager beatmapManager) {
        this.game = game;
        this.screens = screens;
        this.camera = camera;
        this.musicManager = musicManager;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
        this.gameScreen = screens.get(GameScreen.class);
        this.batch = batch;
        this.viewport = viewport;
        this.beatmapManager = beatmapManager;
        this.statsManager = statsManager;
        beatmaps = beatmapManager.getBeatmapsByGroupId();
    }

    @Override
    public void show() {
        currentBeatmap = beatmaps.get(cafe.getBeatmapGroupId()).get().head();
        currentStageNumber = 0;

        cafe.highScore = statsManager.getScore(currentBeatmap.getBeatmapId())   ;
        cinema.highScore = statsManager.getScore(currentBeatmap.getBeatmapId());
        hospital.highScore = statsManager.getScore(currentBeatmap.getBeatmapId());
        school.highScore = statsManager.getScore(currentBeatmap.getBeatmapId());
        home.highScore = statsManager.getScore(currentBeatmap.getBeatmapId());
        office.highScore = statsManager.getScore(currentBeatmap.getBeatmapId());

        if (musicManager.getMusicPlayStatus(MUSIC_BG1)) {
            musicManager.stopMusic(MUSIC_BG1);
        }
        cooldown = MUSIC_PREVIEW_DELAY;
    }

    @Override
    public void render(float delta) {
        clearScreen(253, 243, 255, 1);
        //camera.update();
        //game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        // Show the logo and clouds
        game.batch.draw(bg, 0, 0, vw, vh);
        game.batch.draw(trashworldLogo, vw / 2.8F, vh / 2.8F, vw / 3.7F, vh / 3.5F);

        // Show the header + footer of the game
        game.batch.draw(header, 0, vh / 1.12F, vw / 1.5F, findRatio(1920, 260, vw / 1.5F, 'h'));
        game.batch.draw(footer, 0, 0, vw, findRatio(1920, 72, vw, 'h'));

        // Show the button interfaces
//        game.batch.draw(buttonContinue, vw / 64, 0, findRatio(276, 54, vh/16F, 'w'), vh / 16);
//        game.batch.draw(buttonBack, vw / 1.15F, 0, findRatio(180, 54, vh/16F, 'w'), vh / 16);
        game.batch.draw(buttonContinue, vw / 1.24F, 0, findRatio(276, 54, vh/16F, 'w'), vh / 16);
        game.batch.draw(buttonBack, vw / 64, 0, findRatio(180, 54, vh/16F, 'w'), vh / 16);
        game.batch.draw(selectArrowLeft, vw / 1.4F, vh / 8, vw / 28, vh / 10);
        game.batch.draw(selectArrowRight, vw / 1.065F, vh / 8, vw / 28, vh / 10);

        int count = 0;
        String stageNameToShow = "";
        int highScoreToShow;

        // Show the text of the selected item
        switch (currentStageNumber) {
            case (0):
                highScoreToShow = cafe.highScore;
                stageNameToShow = "DIRTY CAFE";
                game.batch.draw(bdCafeShow, vw / 2, vh / 1.55F, vw / 6, vw / 9);

                currentBeatmap = beatmaps.get(cafe.getBeatmapGroupId()).get().head();

                if (cooldown <= 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_1_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);
                }
                break;
            case (1):
                highScoreToShow = cinema.highScore;
                stageNameToShow = "MESSY CINEMA";
                game.batch.draw(bdCinemaShow, vw / 1.57F, vh / 2.25F, vw / 6, vh / 3);

                currentBeatmap = beatmaps.get(cinema.getBeatmapGroupId()).get().head();

                if (cooldown <= 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_2_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);
                }
                break;
            case (2):
                highScoreToShow = home.highScore;
                stageNameToShow = "DISORDER HOME";
                game.batch.draw(bdHospitalShow, vw / 1.7F, vh / 3.8F, vw / 5, vh / 4);

                currentBeatmap = beatmaps.get(hospital.getBeatmapGroupId()).get().head();

                if (cooldown <= 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_3_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);
                }
                break;
            case (3):
                highScoreToShow = school.highScore;
                stageNameToShow = "SCRUFFY HOSPITAL";

                game.batch.draw(bdSchoolShow, vw / 2.8F, vh / 7.9F, vw / 4, vh / 4);

                currentBeatmap = beatmaps.get(school.getBeatmapGroupId()).get().head();

                if (cooldown <= 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_4_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);
                }
                break;
            case (4):
                highScoreToShow = office.highScore;
                stageNameToShow = "TRASH OFFICE";
                game.batch.draw(bdHomeShow, vw / 5F, vh / 4.15F, vw / 4.2F, vh / 2.5F);

                currentBeatmap = beatmaps.get(home.getBeatmapGroupId()).get().head();

                if (cooldown <= 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_5_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);
                }
                break;
            case (5):
                highScoreToShow = school.highScore;
                stageNameToShow = "NASTY SCHOOL";
                game.batch.draw(bdOfficeShow, vw / 3.7F, vh / 1.68F, vw / 4.2F, vh / 4);

                currentBeatmap = beatmaps.get(office.getBeatmapGroupId()).get().head();

                if (cooldown <= 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_6_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);
                }
                break;
            default:
                currentStageNumber %= 5;
                highScoreToShow = 0;
                stageNameToShow = "Select a stage";
        }

        gameScreen.setBeatmap(currentBeatmap);

        // Show the stage building
        if (currentStageNumber != 0) {
            game.batch.draw(bdCafeHide, vw / 2, vh / 1.55F, vw / 6, vw / 9);
        }
        if (currentStageNumber != 1) {
            game.batch.draw(bdCinemaHide, vw / 1.57F, vh / 2.25F, vw / 6, vh / 3);
        }
        if (currentStageNumber != 2) {
            game.batch.draw(bdHospitalHide, vw / 1.7F, vh / 3.8F, vw / 5, vh / 4);
        }
        if (currentStageNumber != 3) {
            game.batch.draw(bdSchoolHide, vw / 2.8F, vh / 7.9F, vw / 4, vh / 4);
        }
        if (currentStageNumber != 4) {
            game.batch.draw(bdHomeHide, vw / 5F, vh / 4.15F, vw / 4.2F, vh / 2.5F);
        }
        if (currentStageNumber != 5) {
            game.batch.draw(bdOfficeHide, vw / 3.7F, vh / 1.68F, vw / 4.2F, vh / 4);
        }

        font_StageName.draw(batch, stageNameToShow, vw / 64, vh / 1.026F);
        font_HighScore.draw(batch, "High Score", vw / 1.302F, vh / 4.7F);
        font_HighScore.draw(batch, Integer.toString(highScoreToShow), vw / 1.302F, vh / 6.1F);

        if (cooldown > 0) cooldown--;

        if (modes == 0) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)) {
                //game.batch.draw(selectArrowRight, vw/1.04F, vh/8, vw/28, vh/10);
                currentStageNumber = (currentStageNumber + 1) % 6;
                cooldown = MUSIC_PREVIEW_DELAY;
                musicManager.stopMusic();
                musicManager.playMusic(SFX_STAGE_CHANGE);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)) {
                //game.batch.draw(selectArrowLeft, vw/1.45F, vh/8, vw/28, vh/10);
                currentStageNumber += 6;
                currentStageNumber = (currentStageNumber - 1) % 6;
                cooldown = MUSIC_PREVIEW_DELAY;
                musicManager.stopMusic();
                musicManager.playMusic(SFX_STAGE_CHANGE);
            }
        }

        /*if ((modes == 0) && (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))) {
            modes++;
            font_HighScore.draw(game.batch, "Fuck you",getViewportWidth() - 100,getViewportHeight() - 100);
        }*/

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setLazyScreen(screens.get(MenuScreen.class));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameScreen.setBeatmap(currentBeatmap);
            Path musicFile = currentBeatmap.getPath().resolveSibling(currentBeatmap.getGenerals().getAudioFileName());
            gameScreen.setMusic(Gdx.audio.newMusic(Gdx.files.absolute(musicFile.toString())));
            game.setLazyScreen(gameScreen);
        }

        game.batch.draw(cloud, vw / 6.5F, vh / 2.6F, vw / 1.3F, vh / 2);
        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Stage Selection Screen");
        // Debug zone

        game.batch.end();
        times++;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    @Override
    public void loadAssets(Assets assets) {
        assets.load(STAGE_BD_SHOW_OFFICE, TEXTURE);
        assets.load(STAGE_BD_SHOW_CINEMA, TEXTURE);
        assets.load(STAGE_BD_SHOW_HOSPITAL, TEXTURE);
        assets.load(STAGE_BD_SHOW_SCHOOL, TEXTURE);
        assets.load(STAGE_BD_SHOW_HOME, TEXTURE);
        assets.load(STAGE_BD_SHOW_CAFE, TEXTURE);

        assets.load(STAGE_BD_HIDE_OFFICE, TEXTURE);
        assets.load(STAGE_BD_HIDE_CINEMA, TEXTURE);
        assets.load(STAGE_BD_HIDE_HOSPITAL, TEXTURE);
        assets.load(STAGE_BD_HIDE_SCHOOL, TEXTURE);
        assets.load(STAGE_BD_HIDE_HOME, TEXTURE);
        assets.load(STAGE_BD_HIDE_CAFE, TEXTURE);

        assets.load(GLOBAL_ICON_BACK, TEXTURE);
        assets.load(GLOBAL_ICON_CONTINUE, TEXTURE);
        assets.load(STAGE_BG_CLOUD, TEXTURE);
        assets.load(STAGE_BG_HEADER, TEXTURE);
        assets.load(GLOBAL_FOOTER_BAR, TEXTURE);
        assets.load(STAGE_BG_TRASHWORLD, TEXTURE);
        assets.load(STAGE_BG_ARROW_L, TEXTURE);
        assets.load(STAGE_BG_ARROW_R, TEXTURE);
        assets.load(STAGE_BG, TEXTURE);

        assets.load(MUSIC_1_SONG, MUSIC);
        assets.load(MUSIC_2_SONG, MUSIC);
        assets.load(MUSIC_3_SONG, MUSIC);
        assets.load(MUSIC_4_SONG, MUSIC);
        assets.load(MUSIC_5_SONG, MUSIC);
        assets.load(MUSIC_6_SONG, MUSIC);

        assets.load(SFX_STAGE_CHANGE, MUSIC);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bdOfficeShow = assets.get(STAGE_BD_SHOW_OFFICE, TEXTURE); // 2408 × 1356
        this.bdCinemaShow = assets.get(STAGE_BD_SHOW_CINEMA, TEXTURE); // 1539 × 1901
        this.bdHospitalShow = assets.get(STAGE_BD_SHOW_HOSPITAL, TEXTURE); // 1919 × 1402
        this.bdSchoolShow = assets.get(STAGE_BD_SHOW_SCHOOL, TEXTURE); // 2489 × 1372
        this.bdHomeShow = assets.get(STAGE_BD_SHOW_HOME, TEXTURE); // 2176 × 2164
        this.bdCafeShow = assets.get(STAGE_BD_SHOW_CAFE, TEXTURE); // 1608 x 1062

        this.bdOfficeHide = assets.get(STAGE_BD_HIDE_OFFICE, TEXTURE);
        this.bdCinemaHide = assets.get(STAGE_BD_HIDE_CINEMA, TEXTURE);
        this.bdHospitalHide = assets.get(STAGE_BD_HIDE_HOSPITAL, TEXTURE);
        this.bdSchoolHide = assets.get(STAGE_BD_HIDE_SCHOOL, TEXTURE);
        this.bdHomeHide = assets.get(STAGE_BD_HIDE_HOME, TEXTURE);
        this.bdCafeHide = assets.get(STAGE_BD_HIDE_CAFE, TEXTURE);

        this.buttonBack = assets.get(GLOBAL_ICON_BACK, TEXTURE); // 687  × 236
        this.buttonContinue = assets.get(GLOBAL_ICON_CONTINUE, TEXTURE); // 670  × 239
        this.header = assets.get(STAGE_BG_HEADER, TEXTURE); // 4485 × 608
        this.footer = assets.get(GLOBAL_FOOTER_BAR, TEXTURE); // 8002 × 296
        this.cloud = assets.get(STAGE_BG_CLOUD, TEXTURE); // 7507 × 2644
        this.trashworldLogo = assets.get(STAGE_BG_TRASHWORLD, TEXTURE); // 2265 × 1370
        // UNUSED this.overlayBackground = assets.get(STAGE_BG_OVERLAY, TEXTURE); // 6464 × 4460
        this.bg = assets.get(STAGE_BG, TEXTURE); // 1920 × 1080
        this.selectArrowLeft = assets.get(STAGE_BG_ARROW_L, TEXTURE); // 291 × 456
        this.selectArrowRight = assets.get(STAGE_BG_ARROW_R, TEXTURE); // 291 × 456

        this.music1 = assets.get(MUSIC_1_SONG, MUSIC);
        this.music2 = assets.get(MUSIC_2_SONG, MUSIC);
        this.music3 = assets.get(MUSIC_3_SONG, MUSIC);
        this.music4 = assets.get(MUSIC_4_SONG, MUSIC);
        this.music5 = assets.get(MUSIC_5_SONG, MUSIC);
        this.music6 = assets.get(MUSIC_6_SONG, MUSIC);

        this.SFxStageChange = assets.get(SFX_STAGE_CHANGE, MUSIC);

        this.font_HighScore = assets.get8bitFont(33, Color.BLACK);
        this.font_StageName = assets.getSuperSpaceFont(100, Color.WHITE);
    }

    @Override
    public void hide() {
        currentStageNumber = 0;
        cooldown = 0;
        musicManager.stopMusic();
    }

    private List<Building> getBuildings() {
        return List.of(

        );
    }

    public int getTimes() {
        return times;
    }
}

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
import com.trashmelody.constants.Beatmaps;
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
    private TrashMelody game;
    private ScreenProvider screens;
    private OrthographicCamera camera;
    private MusicManager musicManager;
    private Assets assets;
    private Viewport viewport;
    private GameScreen gameScreen;
    private SpriteBatch batch;
    private BeatmapManager beatmapManager;
    private Map<String, Stream<Beatmap>> beatmaps;

    private Building cafe = new Building("Cafe", Beatmaps.HITORIGOTO_BEATMAP_GROUP_ID);
    private Building cinema = new Building("Cinema", Beatmaps.MARBLE_SODA_BEATMAP_GROUP_ID);
    private Building hospital = new Building("Hospital", Beatmaps.REUNION_BEATMAP_GROUP_ID);
    private Building school = new Building("School", Beatmaps.KANASHII_URESHII_BEATMAP_GROUP_ID);
    private Building home = new Building("Home", Beatmaps.MIRROR_BEATMAP_GROUP_ID);
    private Building office = new Building("Office", Beatmaps.STEP_AHEAD_BEATMAP_GROUP_ID);

    // Defining building value
    private Texture bdHomeShow, bdCafeShow, bdCinemaShow, bdHospitalShow, bdSchoolShow, bdOfficeShow;
    private Texture bdHomeHide, bdCafeHide, bdCinemaHide, bdHospitalHide, bdSchoolHide, bdOfficeHide;
    private Texture stageHomeText, stageCafeText, stageCinemaText, stageHospitalText, stageSchoolText, stageOfficeText;
    private Texture buttonBack, buttonPlay, header, footer, cloud, trashworldLogo, selectArrowLeft, selectArrowRight, bg;

    private BitmapFont font;

    private Music music1, music2, music3, music4, music5, music6;

    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private int currentStageNumber = 0;
    private int modes, cooldown;

    StatsManager statsManager = new StatsManager();

    @Inject
    StageSelectScreen(TrashMelody game,
                      OrthographicCamera camera,
                      ScreenProvider screens,
                      MusicManager musicManager,
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

        beatmaps = beatmapManager.getBeatmapsByGroupId();
    }

    @Override
    public void show() {
        if (musicManager.getMusicPlayStatus(MUSIC_BG1)) {
            musicManager.stopMusic(MUSIC_BG1);
        }
        cooldown = 50;
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
        game.batch.draw(header, 0, vh / 1.12F, vw / 1.5F, findRatio(1920, 260, vw/1.5F, 'h'));
        game.batch.draw(footer, 0, 0, vw, findRatio(1920, 72, vw, 'h'));

        // Show the button interfaces
        game.batch.draw(buttonPlay, vw / 64, 0, findRatio(176, 54, vh/16F, 'w'), vh / 16);
        game.batch.draw(buttonBack, vw / 1.15F, 0, findRatio(180, 54, vh/16F, 'w'), vh / 16);
        game.batch.draw(selectArrowLeft, vw / 1.4F, vh / 8, vw / 28, vh / 10);
        game.batch.draw(selectArrowRight, vw / 1.065F, vh / 8, vw / 28, vh / 10);

        font.draw(batch, "High Score", vw / 1.3F, vh / 4.5F);

        int count = 0;

        Beatmap currentBeatmap = null;

        // Show the text of the selected item
        switch (currentStageNumber) {
            case (0):
                font.draw(batch, Integer.toString(statsManager.getStageScore("stage1Score")), vw / 1.3F, vh / 6);
                game.batch.draw(stageCafeText, vw / 64, vh / 1.143F, vw / 3.5F, vh / 8);
                game.batch.draw(bdCafeShow, vw / 2, vh / 1.55F, vw / 6, vw / 9);

                currentBeatmap = beatmaps.get(cafe.getBeatmapGroupId()).get().head();

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_1_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);
                }
                break;
            case (1):
                font.draw(batch, Integer.toString(statsManager.getStageScore("stage2Score")), vw / 1.3F, vh / 6);
                game.batch.draw(stageCinemaText, vw / 64, vh / 1.143F, vw / 2.6F, vh / 8);
                game.batch.draw(bdCinemaShow, vw / 1.57F, vh / 2.25F, vw / 6, vh / 3);

                currentBeatmap = beatmaps.get(cinema.getBeatmapGroupId()).get().head();

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_2_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (2):
                font.draw(batch, Integer.toString(statsManager.getStageScore("stage3Score")), vw / 1.3F, vh / 6);
                game.batch.draw(stageHospitalText, vw / 64, vh / 1.143F, vw / 2, vh / 8);
                game.batch.draw(bdHospitalShow, vw / 1.7F, vh / 3.8F, vw / 5, vh / 4);

                currentBeatmap = beatmaps.get(hospital.getBeatmapGroupId()).get().head();

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_3_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (3):
                font.draw(batch, Integer.toString(statsManager.getStageScore("stage4Score")), vw / 1.3F, vh / 6);
                game.batch.draw(bdSchoolShow, vw / 2.8F, vh / 7.9F, vw / 4, vh / 4);
                game.batch.draw(stageSchoolText, vw / 64, vh / 1.143F, vw / 2.5F, vh / 8);

                currentBeatmap = beatmaps.get(school.getBeatmapGroupId()).get().head();

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_4_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (4):
                font.draw(batch, Integer.toString(statsManager.getStageScore("stage5Score")), vw / 1.3F, vh / 6);
                game.batch.draw(bdHomeShow, vw / 5F, vh / 4.15F, vw / 4.2F, vh / 2.5F);
                game.batch.draw(stageHomeText, vw / 64, vh / 1.143F, vw / 2.5F, vh / 8);

                currentBeatmap = beatmaps.get(home.getBeatmapGroupId()).get().head();

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_5_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            case (5):
                font.draw(batch, Integer.toString(statsManager.getStageScore("stage6Score")), vw / 1.3F, vh / 6);
                game.batch.draw(bdOfficeShow, vw / 3.7F, vh / 1.68F, vw / 4.2F, vh / 4);
                game.batch.draw(stageOfficeText, vw / 64, vh / 1.143F, vw / 2.5F, vh / 8);

                currentBeatmap = beatmaps.get(office.getBeatmapGroupId()).get().head();

                if (cooldown == 0) {
                    cooldown--;
                    musicManager.setDefault(MUSIC_6_SONG);
                    musicManager.playMusic(.3F);
//                    musicManager.setMusicPosition(52);

                }
                break;
            default:
                currentStageNumber %= 5;
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

        if (cooldown > 0)
            cooldown--;


        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            game.setLazyScreen(screens.get(MenuScreen.class));
        }

        /*if (Gdx.input.isKeyJustPressed(Input.Keys.C)){
            game.setLazyScreen(screens.get(GameScreen.class));
        }*/

        if (modes == 0) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)) {
                //game.batch.draw(selectArrowRight, vw/1.04F, vh/8, vw/28, vh/10);
                currentStageNumber = (currentStageNumber + 1) % 6;
                cooldown = 50;
                musicManager.stopMusic();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)) {
                //game.batch.draw(selectArrowLeft, vw/1.45F, vh/8, vw/28, vh/10);
                currentStageNumber = (currentStageNumber - 1) % 6;
                if (currentStageNumber < 0) {
                    currentStageNumber = 5;
                }
                cooldown = 50;
                musicManager.stopMusic();
            }
        }

        /*if ((modes == 0) && (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))) {
            modes++;
            font.draw(game.batch, "Fuck you",getViewportWidth() - 100,getViewportHeight() - 100);
        }*/

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

        assets.load(STAGE_TEXT_OFFICE, TEXTURE);
        assets.load(STAGE_TEXT_CINEMA, TEXTURE);
        assets.load(STAGE_TEXT_HOSPITAL, TEXTURE);
        assets.load(STAGE_TEXT_SCHOOL, TEXTURE);
        assets.load(STAGE_TEXT_HOME, TEXTURE);
        assets.load(STAGE_TEXT_CAFE, TEXTURE);
        assets.load(GLOBAL_ICON_BACK, TEXTURE);
        assets.load(GLOBAL_ICON_PLAY, TEXTURE);
        assets.load(STAGE_BG_CLOUD, TEXTURE);
        assets.load(STAGE_BG_HEADER, TEXTURE);
        assets.load(GLOBAL_FOOTER_BAR, TEXTURE);
        assets.load(STAGE_BG_TRASHWORLD, TEXTURE);
        // UNUSED assets.load(STAGE_BG_OVERLAY, TEXTURE);
        assets.load(STAGE_BG_ARROW_L, TEXTURE);
        assets.load(STAGE_BG_ARROW_R, TEXTURE);
        assets.load(STAGE_BG, TEXTURE);
        assets.load(MUSIC_1_SONG, MUSIC);
        assets.load(MUSIC_2_SONG, MUSIC);
        assets.load(MUSIC_3_SONG, MUSIC);
        assets.load(MUSIC_4_SONG, MUSIC);
        assets.load(MUSIC_5_SONG, MUSIC);
        assets.load(MUSIC_6_SONG, MUSIC);
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

        this.stageHomeText = assets.get(STAGE_TEXT_HOME, TEXTURE); // 2826 × 487
        this.stageOfficeText = assets.get(STAGE_TEXT_OFFICE, TEXTURE); // 2507 × 487
        this.stageCafeText = assets.get(STAGE_TEXT_CAFE, TEXTURE); // 2057 × 487
        this.stageCinemaText = assets.get(STAGE_TEXT_CINEMA, TEXTURE); // 2601 × 487
        this.stageHospitalText = assets.get(STAGE_TEXT_HOSPITAL, TEXTURE); // 3428 × 487
        this.stageSchoolText = assets.get(STAGE_TEXT_SCHOOL, TEXTURE); // 2702 × 487

        this.buttonBack = assets.get(GLOBAL_ICON_BACK, TEXTURE); // 687  × 236
        this.buttonPlay = assets.get(GLOBAL_ICON_PLAY, TEXTURE); // 670  × 239
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

        this.font = assets.get8bitFont(24, Color.GREEN);

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
}

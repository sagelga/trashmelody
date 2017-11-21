package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.managers.TrashManager;
import com.trashmelody.models.trashes.TrashType;
import com.trashmelody.models.trashes.dangerous.Cigarette;
import com.trashmelody.models.trashes.dangerous.OilCan;
import com.trashmelody.models.trashes.recycle.Paper;
import com.trashmelody.models.trashes.wet.Popcorn;
import com.trashmelody.utils.Debugger;
import com.trashmelody.TrashMelody;
import com.trashmelody.utils.GifDecoder;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class GuideCardScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private TrashManager trashManager;
    private int count = 1;
    private Animation<TextureRegion> bg, anykey;
    private Texture footer, header2, buttonPlay;
    //Dangerous Trashes
    private Texture cigar, spray, can, thinner;
    //Recycle Trashes
    private Texture cardboard, glass, note, paper, plastic, bottle;
    //Wet Trashes
    private Texture curry, donut, icecream, matcha, popcorn;
    //General Trashes
    private Texture plate, tooth, cloth, pencil, bag;
    //Bin
    private Texture dangerBin, recycleBin, wetBin, idkBin;
    //Key
    private Texture key_d, key_f, key_j, key_k;
    //Temporary Current Stage
    private int currentStage = 1;
    float elapsed;
    private GlyphLayout layoutTitle = new GlyphLayout();
    private GlyphLayout layoutDesc = new GlyphLayout();
    private GlyphLayout layoutTrashType = new GlyphLayout();

    @Inject
    GuideCardScreen(TrashMelody game, OrthographicCamera camera, ScreenProvider screens, Viewport viewport, SpriteBatch batch, TrashManager trashManager) {
        this.game = game;
        this.screens = screens;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
        this.trashManager = trashManager;
        this.batch = batch;
    }

    @Override
    public void render(float delta) {
        elapsed += delta;
        clearScreen();

        game.batch.begin();
        //Animation Press Anykey

        // Draw Background
        if (TrashMelody.enableAnimation) {
            game.batch.draw(bg.getKeyFrame(elapsed), 0, 0, findRatio(16, 9, vh, 'w'), vh);
            game.batch.draw(anykey.getKeyFrame(elapsed), (vw/2)-((vw/1.6F)/2), vh/21, vw/1.6F, findRatio(8, 1, vh, 'h'));
        } else {
            game.batch.draw(bg.getKeyFrame(0), 0, 0, findRatio(16, 9, vh, 'w'), vh);
            game.batch.draw(anykey.getKeyFrame(0), (vw/2)-((vw/1.6F)/2), vh/21, vw/1.6F, findRatio(8, 1, vh, 'h'));
        }
        
        //Trash Unlocked 16 (0-15)Case
        String[] trashName = {"Botty", "Pep","Popu-san", "dish - dash", "Cigar", "SaiSai", "Keri-a", "toothpast",
                "Hairspray-chan", "MookMook", "Matty", "Raggy", "Oily Oiler", "The Trio", "Izu-chan", "Immortal bag"};
        Texture[] trashTexture = {bottle, note,popcorn,plate,cigar,plastic,curry,tooth,spray,glass
        ,matcha,cloth,can,paper,icecream,bag};
        int currentUnlock = -1;
        if(currentUnlock < 0) {
            game.batch.draw(header2, 0, vh / 1.12F, vw, vh / 8);
            game.batch.draw(thinner, vw / 12, vh / 3.1F, vw / 5, vh / 2.2F);
            game.batch.draw(cardboard, vw / 3.45F, vh / 3.1F, vw / 5, vh / 2.2F);
            game.batch.draw(donut, vw / 2, vh / 3.1F, vw / 5, vh / 2.2F);
            game.batch.draw(pencil, vw / 1.4F, vh / 3.1F, vw / 5, vh / 2.2F);
            game.batch.draw(dangerBin, vw / 4.5F, vh / 1.45F, vw / 19.5F, vh / 14);
            game.batch.draw(recycleBin, vw / 2.33F, vh / 1.45F, vw / 19, vh / 14);
            game.batch.draw(wetBin, vw / 1.565F, vh / 1.45F, vw / 19, vh / 14);
            game.batch.draw(idkBin, vw / 1.169F, vh / 1.45F, vw / 19, vh / 14);
            game.batch.draw(key_d, vw / 6, vh / 4.5F, vw / 22F, vh / 14);
            game.batch.draw(key_f, vw / 2.7F, vh / 4.5F, vw / 22F, vh / 14);
            game.batch.draw(key_j, vw / 1.75F, vh / 4.5F, vw / 22F, vh / 14);
            game.batch.draw(key_k, vw / 1.25F, vh / 4.5F, vw / 22F, vh / 14);
        }
        for (int i = 0; i <= currentUnlock; i++) {
            if (trashManager.getTrashByName(trashName[i]).getUnlockAt() == currentUnlock) {
                game.batch.draw(trashTexture[i], vw / 2.5F, vh / 3.1F, vw / 5, vh / 2.2F);//trashname texture
                if (trashManager.getTrashByName(trashName[i]).getType() == TrashType.Dangerous) {
                    game.batch.draw(dangerBin, vw / 1.85F, vh / 1.4F, vw / 19.5F, vh / 14);
                } else if (trashManager.getTrashByName(trashName[i]).getType() == TrashType.General) {
                    game.batch.draw(idkBin, vw / 1.85F, vh / 1.4F, vw / 19.5F, vh / 14);
                } else if (trashManager.getTrashByName(trashName[i]).getType() == TrashType.Wet) {
                    game.batch.draw(wetBin, vw / 1.85F, vh / 1.4F, vw / 19.5F, vh / 14);
                } else if (trashManager.getTrashByName(trashName[i]).getType() == TrashType.Recycle) {
                    game.batch.draw(recycleBin, vw / 1.85F, vh / 1.4F, vw / 19.5F, vh / 14);
                }
                break;
            }
            else if(i == trashName.length){
                break;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setLazyScreen(screens.get(StageSelectScreen.class));
        }

        // Debug zone
//        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font, "Collection Screen");
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
        assets.load(COLLECTION_BG, TEXTURE);
        assets.load(COLLECTION_HEADER2, TEXTURE);
        assets.load(COLLECTION_PACK, TEXTURE);
        assets.load(COLLECTION_LEFT, TEXTURE);
        assets.load(COLLECTION_RIGHT, TEXTURE);
        assets.load(COLLECTION_LEFT_H, TEXTURE);
        assets.load(COLLECTION_RIGHT_H, TEXTURE);
        assets.load(COLLECTION_STORY_BG, TEXTURE);
        assets.load(GLOBAL_ICON_BACK, TEXTURE);
        assets.load(GLOBAL_FOOTER_BAR, TEXTURE);
        //Trashes
        assets.load(COLLECTION_DANGER_1, TEXTURE);
        assets.load(COLLECTION_DANGER_2, TEXTURE);
        assets.load(COLLECTION_DANGER_3, TEXTURE);
        assets.load(COLLECTION_DANGER_4, TEXTURE);
        assets.load(COLLECTION_DANGER_5, TEXTURE);
        assets.load(COLLECTION_RECYCLE_1, TEXTURE);
        assets.load(COLLECTION_RECYCLE_2, TEXTURE);
        assets.load(COLLECTION_RECYCLE_3, TEXTURE);
        assets.load(COLLECTION_RECYCLE_4, TEXTURE);
        assets.load(COLLECTION_RECYCLE_5, TEXTURE);
        assets.load(COLLECTION_WET_1, TEXTURE);
        assets.load(COLLECTION_WET_2, TEXTURE);
        assets.load(COLLECTION_WET_3, TEXTURE);
        assets.load(COLLECTION_WET_4, TEXTURE);
        assets.load(COLLECTION_WET_5, TEXTURE);
        assets.load(GLOBAL_ICON_PLAY, TEXTURE);
        assets.load(COLLECTION_GENERAL_1, TEXTURE);
        assets.load(COLLECTION_GENERAL_2, TEXTURE);
        assets.load(COLLECTION_GENERAL_3, TEXTURE);
        assets.load(COLLECTION_GENERAL_4, TEXTURE);
        assets.load(COLLECTION_GENERAL_5, TEXTURE);
        //Bin
        assets.load(GAME_BIN_01, TEXTURE);
        assets.load(GAME_BIN_02, TEXTURE);
        assets.load(GAME_BIN_03, TEXTURE);
        assets.load(GAME_BIN_04, TEXTURE);
        //Key
        assets.load(GUIDE_ICON_D, TEXTURE);
        assets.load(GUIDE_ICON_F, TEXTURE);
        assets.load(GUIDE_ICON_J, TEXTURE);
        assets.load(GUIDE_ICON_K, TEXTURE);
        assets.load(GUIDE_ANYKEY, TEXTURE);

        this.bg = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(COLLECTION_BG).read());
        this.anykey = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal(GUIDE_ANYKEY).read());
    }

    @Override
    public void afterLoad(Assets assets) {
        this.footer = assets.get(GLOBAL_FOOTER_BAR, TEXTURE);
        this.header2 = assets.get(COLLECTION_HEADER2, TEXTURE);
        this.buttonPlay = assets.get(GLOBAL_ICON_PLAY, TEXTURE);
        //Danger Trashes
        this.bag = assets.get(COLLECTION_DANGER_1, TEXTURE);
        this.spray = assets.get(COLLECTION_DANGER_2, TEXTURE);
        this.cigar = assets.get(COLLECTION_DANGER_3, TEXTURE);
        this.thinner = assets.get(COLLECTION_DANGER_4, TEXTURE);
        this.can = assets.get(COLLECTION_DANGER_5, TEXTURE);
        //Recycle Trashes
        this.paper = assets.get(COLLECTION_RECYCLE_1, TEXTURE);
        this.note = assets.get(COLLECTION_RECYCLE_2, TEXTURE);
        this.plastic = assets.get(COLLECTION_RECYCLE_3, TEXTURE);
        this.glass = assets.get(COLLECTION_RECYCLE_4, TEXTURE);
        this.cardboard = assets.get(COLLECTION_RECYCLE_5, TEXTURE);
        //Wet Trashes
        this.popcorn = assets.get(COLLECTION_WET_1, TEXTURE);
        this.donut = assets.get(COLLECTION_WET_2, TEXTURE);
        this.curry = assets.get(COLLECTION_WET_3, TEXTURE);
        this.matcha = assets.get(COLLECTION_WET_4, TEXTURE);
        this.icecream = assets.get(COLLECTION_WET_5, TEXTURE);
        //General Trashes
        this.bottle = assets.get(COLLECTION_GENERAL_1, TEXTURE);
        this.plate = assets.get(COLLECTION_GENERAL_2, TEXTURE);
        this.tooth = assets.get(COLLECTION_GENERAL_3, TEXTURE);
        this.cloth = assets.get(COLLECTION_GENERAL_4, TEXTURE);
        this.pencil = assets.get(COLLECTION_GENERAL_5, TEXTURE);
        //Bin
        this.dangerBin = assets.get(GAME_BIN_01, TEXTURE);
        this.recycleBin = assets.get(GAME_BIN_02, TEXTURE);
        this.wetBin = assets.get(GAME_BIN_03, TEXTURE);
        this.idkBin = assets.get(GAME_BIN_04, TEXTURE);
        //Key
        this.key_d = assets.get(GUIDE_ICON_D, TEXTURE);
        this.key_f = assets.get(GUIDE_ICON_F, TEXTURE);
        this.key_j = assets.get(GUIDE_ICON_J, TEXTURE);
        this.key_k = assets.get(GUIDE_ICON_K, TEXTURE);
    }
}

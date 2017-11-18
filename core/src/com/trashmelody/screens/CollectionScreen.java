package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.managers.Assets;
import com.trashmelody.managers.ScreenProvider;
import com.trashmelody.utils.Debugger;
import com.trashmelody.TrashMelody;

import static com.trashmelody.managers.Assets.*;
import static com.trashmelody.utils.RenderingUtils.*;

@Singleton
public class CollectionScreen extends LazyScreen {
    private TrashMelody game;
    private ScreenProvider screens;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Texture bg, screenTitle, buttonBack, footer, bar, arrow, textExample;
    private Texture lv1, lv2, lv3, lv4, lv5, lv6, dangerBag, reTrio, wetCorn;
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private int count = 1;

    @Inject
    CollectionScreen(TrashMelody game, OrthographicCamera camera, ScreenProvider screens, Viewport viewport) {
        this.game = game;
        this.screens = screens;
        this.camera = camera;
        this.viewport = new ScalingViewport(Scaling.fit, vw, vh, camera);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        game.batch.begin();

        game.batch.draw(bg, 0, 0, vw, vh);
        game.batch.draw(screenTitle, 0, vh/1.35F,vw/1.5F,vh/4);
        game.batch.draw(bar, vw/2.5F, vh/64, vw/1.5F, vh/1.2F);
        game.batch.draw(footer, 0, 0, vw, vh/12);
        game.batch.draw(buttonBack, vw/1.13F, 0, vw/10, vh/16);
        game.batch.draw(dangerBag, vw/64, vh/9, vw/3.5F, vh/1.5F);
        game.batch.draw(dangerBag, vw/19, vh/9, vw/3.5F, vh/1.5F);

        game.batch.draw(arrow, vw/20, vh/9, vw/3, vh/12);
        game.batch.draw(arrow, vw/28, vh/9, vw/3, vh/12);

        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            game.setLazyScreen(screens.get(MenuScreen.class));
        }

        if (count == 1) {
            game.batch.draw(dangerBag, vw / 11, vh / 9, vw / 3.5F, vh / 1.5F);
            game.batch.draw(textExample, vw / 2.5F, vh / 3.5F, vw / 1.8F, vh / 1.6F);
            game.batch.draw(lv1, vw / 1.7F, vh / 7, vw / 4, vh / 5);
        }
        else if (count == 2) {
            game.batch.draw(reTrio, vw / 11, vh / 9, vw / 3.5F, vh / 1.5F);
            game.batch.draw(lv2, vw / 1.7F, vh / 7, vw / 4, vh / 5);
        }
        else if (count == 3) {
            game.batch.draw(wetCorn, vw / 11, vh / 9, vw / 3.5F, vh / 1.5F);
            game.batch.draw(lv6, vw / 1.7F, vh / 7, vw / 4, vh / 5);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)) count++;
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)) count--;

        // Reset counter
        if (count > 3) count = 1;
        else if (count < 1) count = 3;

        //arrow pad
        game.batch.draw(arrow, vw/24, vh/9, vw/3, vh/12);

        // Debug zone
        if (Debugger.debug_mode) Debugger.runDebugger(game.batch, game.font,"Collection Screen");
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
        assets.load(COLLECTION_SCREEN_TITLE, TEXTURE);
        assets.load(COLLECTION_BAR, TEXTURE);
        assets.load(COLLECTION_ARROW, TEXTURE);
        assets.load(COLLECTION_TEXT_EXAMPLE, TEXTURE);
        assets.load(COLLECTION_LV1, TEXTURE);
        assets.load(COLLECTION_LV2, TEXTURE);
        assets.load(COLLECTION_LV3, TEXTURE);
        assets.load(COLLECTION_LV4, TEXTURE);
        assets.load(COLLECTION_LV5, TEXTURE);
        assets.load(COLLECTION_LV6, TEXTURE);
        assets.load(STAGE_BG_BACKBUTTON, TEXTURE);
        assets.load(STAGE_BG_FOOTER, TEXTURE);
        assets.load(COLLECTION_CARD_DANGER_BAG, TEXTURE);
        assets.load(COLLECTION_CARD_RECYCLE_TRIO, TEXTURE);
        assets.load(COLLECTION_CARD_WET_POPCORN, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.buttonBack = assets.get(STAGE_BG_BACKBUTTON, TEXTURE);
        this.footer = assets.get(STAGE_BG_FOOTER, TEXTURE);
        this.bg = assets.get(COLLECTION_BG, TEXTURE);
        this.screenTitle = assets.get(COLLECTION_SCREEN_TITLE, TEXTURE);
        this.bar = assets.get(COLLECTION_BAR, TEXTURE);
        this.arrow = assets.get(COLLECTION_ARROW, TEXTURE);
        this.textExample = assets.get(COLLECTION_TEXT_EXAMPLE, TEXTURE);
        this.lv1 = assets.get(COLLECTION_LV1, TEXTURE);
        this.lv2 = assets.get(COLLECTION_LV2, TEXTURE);
        this.lv3 = assets.get(COLLECTION_LV3, TEXTURE);
        this.lv4 = assets.get(COLLECTION_LV4, TEXTURE);
        this.lv5 = assets.get(COLLECTION_LV5, TEXTURE);
        this.lv6 = assets.get(COLLECTION_LV6, TEXTURE);
        this.dangerBag = assets.get(COLLECTION_CARD_DANGER_BAG, TEXTURE);
        this.reTrio = assets.get(COLLECTION_CARD_RECYCLE_TRIO, TEXTURE);
        this.wetCorn = assets.get(COLLECTION_CARD_WET_POPCORN, TEXTURE);
    }
}

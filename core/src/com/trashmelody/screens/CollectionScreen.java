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
    private float vh = getViewportHeight();
    private float vw = getViewportWidth();
    private int count = 1;
    private Texture bg,footer,header,pack,l,r,lh,rh,back;
    //Dangerous Trashes
    private Texture cigar,spray,can,bag,thinner;
    //Recycle Trashes
    private Texture  cardboard,glass,note,paper,plastic;
    //Wet Trashes
    private Texture curry,donut,icecream,matcha,popcorn;


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

        //Draw Background
        game.batch.draw(bg,0,0,vw,vh);
        game.batch.draw(footer, 0, 0, vw, vh / 12);
        game.batch.draw(back, vw / 1.13F, 0, vw / 10, vh / 16);
        game.batch.draw(pack, vw / 4, vw/ 6.5F, vw / 1.8F, vh / 2);
        game.batch.draw(header, vw/128, vh / 1.25F, vw / 2.5F, vh / 5);
        game.batch.draw(l, vw/5.5F, vh / 2, vw / 40, vh / 24);
        game.batch.draw(r, vw/1.2F, vh / 2, vw / 40, vh / 24);

        //Trash on Stage 1
        game.batch.draw(bag,vw/2.35F,vh/3.1F,vw/5,vh/2.2F);


        if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
            game.setLazyScreen(screens.get(MenuScreen.class));
        }



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
        assets.load(COLLECTION_HEADER, TEXTURE);
        assets.load(COLLECTION_PACK, TEXTURE);
        assets.load(COLLECTION_LEFT, TEXTURE);
        assets.load(COLLECTION_RIGHT, TEXTURE);
        assets.load(COLLECTION_LEFT_H, TEXTURE);
        assets.load(COLLECTION_RIGHT_H, TEXTURE);
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
        assets.load(GLOBAL_ICON_BACK, TEXTURE);
        assets.load(GLOBAL_FOOTER_BAR, TEXTURE);
    }

    @Override
    public void afterLoad(Assets assets) {
        this.bg         = assets.get(COLLECTION_BG, TEXTURE);
        this.footer     = assets.get(GLOBAL_FOOTER_BAR, TEXTURE);
        this.header     = assets.get(COLLECTION_HEADER, TEXTURE);
        this.pack       = assets.get(COLLECTION_PACK, TEXTURE);
        this.l          = assets.get(COLLECTION_LEFT, TEXTURE);
        this.r          = assets.get(COLLECTION_RIGHT, TEXTURE);
        this.lh         = assets.get(COLLECTION_LEFT_H, TEXTURE);
        this.rh         = assets.get(COLLECTION_RIGHT_H, TEXTURE);
        this.back       = assets.get(GLOBAL_ICON_BACK, TEXTURE);
        //Danger Trashes
        this.bag        = assets.get(COLLECTION_DANGER_1, TEXTURE);
        this.spray      = assets.get(COLLECTION_DANGER_2, TEXTURE);
        this.cigar      = assets.get(COLLECTION_DANGER_3, TEXTURE);
        this.thinner    = assets.get(COLLECTION_DANGER_4, TEXTURE);
        this.can        = assets.get(COLLECTION_DANGER_5, TEXTURE);
        //Recycle Trashes
        this.paper      = assets.get(COLLECTION_RECYCLE_1, TEXTURE);
        this.note       = assets.get(COLLECTION_RECYCLE_2, TEXTURE);
        this.plastic    = assets.get(COLLECTION_RECYCLE_3, TEXTURE);
        this.glass      = assets.get(COLLECTION_RECYCLE_4, TEXTURE);
        this.cardboard  = assets.get(COLLECTION_RECYCLE_5, TEXTURE);
        //Wet Trashes
        this.popcorn    = assets.get(COLLECTION_WET_1, TEXTURE);
        this.donut      = assets.get(COLLECTION_WET_2, TEXTURE);
        this.curry      = assets.get(COLLECTION_WET_3, TEXTURE);
        this.matcha     = assets.get(COLLECTION_WET_4, TEXTURE);
        this.icecream   = assets.get(COLLECTION_WET_5, TEXTURE);
    }
}

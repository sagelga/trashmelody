package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.google.inject.Inject;
import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;

import static com.trashmelody.Utils.logInputCoordinate;
import static io.vavr.API.println;

public class SettingsScreen extends ScreenAdapter {
    private TrashMelody game;
    private OrthographicCamera camera;
    private Assets assets;

    @Inject
    public SettingsScreen(TrashMelody game, OrthographicCamera camera, Assets assets) {
        this.game = game;
        this.camera = camera;
        this.assets = assets;
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isTouched()) {
            Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            println(touchPosition.x);
            println(touchPosition.y);
        }

        game.batch.begin();
        assets.getSuperSpaceFont().draw(game.batch, "Settings Screen", 30, 40);
        game.batch.end();
    }

}

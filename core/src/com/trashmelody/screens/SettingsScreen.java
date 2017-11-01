package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.google.inject.Inject;
import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;
import com.trashmelody.models.Position;
import com.trashmelody.models.Text;
import io.vavr.collection.List;

import static com.trashmelody.Utils.*;
import static io.vavr.API.println;

public class SettingsScreen extends ScreenAdapter {
    private TrashMelody game;
    private OrthographicCamera camera;
    private Assets assets;
    private List<String> right;
    private List<String> left;
    private String current;

    @Inject
    SettingsScreen(TrashMelody game, OrthographicCamera camera, Assets assets) {
        this.game = game;
        this.camera = camera;
        this.assets = assets;

        right = List.of(
            "Effect Volume",
            "Screen Mode",
            "Calibrate",
            "Default"
        );
        left = List.empty();
        current = "Music Volume";
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.justTouched()) {
            Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            println(touchPosition.x);
            println(touchPosition.y);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !right.isEmpty()) {
            left = left.prepend(current);
            current = right.head();
            right = right.drop(1);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !left.isEmpty()) {
            right = right.prepend(current);
            current = left.head();
            left = left.drop(1);
        }

        game.batch.begin();

        right.zip(List.of(
                Position.of(getCenterX(), getCenterY() - 40),
                Position.of(getCenterX(), getCenterY() - 80),
                Position.of(getCenterX(), getCenterY() - 120)
        )).forEach(element -> {
            assets.getSuperSpaceFont().draw(game.batch, element._1, element._2.x, element._2.y);
        });
        left.zip(List.of(
                Position.of(getCenterX(), getCenterY() + 40),
                Position.of(getCenterX(), getCenterY() + 80),
                Position.of(getCenterX(), getCenterY() + 120)
        )).forEach(element -> {
            assets.getSuperSpaceFont().draw(game.batch, element._1, element._2.x, element._2.y);
        });

        assets.getSuperSpace40PxFont().draw(game.batch, current, getViewportWidth()/2, getViewportHeight()/2);
        assets.getSuperSpace40PxFont().draw(game.batch, "Settings Screen", 30, 40);
        game.batch.end();
    }

}

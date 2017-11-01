package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.inject.Inject;
import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;
import com.trashmelody.models.Position;
import com.trashmelody.models.Button;
import io.vavr.collection.List;

import static com.trashmelody.Utils.*;
import static io.vavr.API.println;

public class SettingsScreen extends ScreenAdapter {
    private TrashMelody game;
    private OrthographicCamera camera;
    private Assets assets;
    private List<String> right;
    private List<Position> rightPosition;
    private List<String> left;
    private List<Position> leftPosition;
    private String current;
    private BitmapFont largeFont;
    private BitmapFont mediumFont;

    @Inject
    SettingsScreen(TrashMelody game, OrthographicCamera camera, Assets assets) {
        this.game = game;
        this.camera = camera;
        this.assets = assets;
        largeFont = assets.getSuperSpaceFont(40, Color.BLACK);
        mediumFont = assets.getSuperSpaceFont(25, Color.BLACK);

        right = List.of(
            "Effect Volume",
            "Screen Mode",
            "Calibrate",
            "Default"
        );
        left = List.empty();
        rightPosition = List.of(
                Position.of(getCenterX(), getCenterY() - 60),
                Position.of(getCenterX(), getCenterY() - 120),
                Position.of(getCenterX(), getCenterY() - 180)
        );
        leftPosition = List.of(
                Position.of(getCenterX(), getCenterY() + 60),
                Position.of(getCenterX(), getCenterY() + 120),
                Position.of(getCenterX(), getCenterY() + 180)
        );
        current = "Music Volume";
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

//        Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//        camera.unproject(touchPosition);

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !right.isEmpty()) {
            left = left.prepend(current);
            current = right.head();
            right = right.tail();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !left.isEmpty()) {
            right = right.prepend(current);
            current = left.head();
            left = left.tail();
        }

        game.batch.begin();
        right.zipWith(rightPosition, Button::new).forEach(button -> {
            mediumFont.draw(game.batch, button.text, button.position.x, button.position.y);
        });
        left.zipWith(leftPosition, Button::new).forEach(button -> {
            mediumFont.draw(game.batch, button.text, button.position.x, button.position.y);
        });
        largeFont.draw(game.batch, current, getViewportWidth()/2, getViewportHeight()/2 + 10);
        mediumFont.draw(game.batch, "Settings Screen", 30, 40);
        game.batch.end();
    }

}

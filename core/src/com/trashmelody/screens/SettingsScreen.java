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
    private List<String> rightSections;
    private List<Position> rightPositions;
    private List<String> leftSections;
    private List<Position> leftPositions;
    private String currentSection;
    private BitmapFont largeFont;
    private BitmapFont mediumFont;

    @Inject
    SettingsScreen(TrashMelody game, OrthographicCamera camera, Assets assets) {
        this.game = game;
        this.camera = camera;
        this.assets = assets;
        largeFont = assets.getSuperSpaceFont(40, Color.BLACK);
        mediumFont = assets.getSuperSpaceFont(25, Color.BLACK);

        leftSections = List.empty();
        rightSections = List.of(
                "Effect Volume",
                "Screen Mode",
                "Calibrate",
                "Default"
        );
        currentSection = "Music Volume";
        leftPositions = List.of(
                Position.of(getCenterX(), getCenterY() + 60),
                Position.of(getCenterX(), getCenterY() + 120),
                Position.of(getCenterX(), getCenterY() + 180)
        );
        rightPositions = List.of(
                Position.of(getCenterX(), getCenterY() - 60),
                Position.of(getCenterX(), getCenterY() - 120),
                Position.of(getCenterX(), getCenterY() - 180)
        );
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

//        Vector3 touchPosition = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//        camera.unproject(touchPosition);

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !rightSections.isEmpty()) {
            leftSections = leftSections.prepend(currentSection);
            currentSection = rightSections.head();
            rightSections = rightSections.tail();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !leftSections.isEmpty()) {
            rightSections = rightSections.prepend(currentSection);
            currentSection = leftSections.head();
            leftSections = leftSections.tail();
        }

        game.batch.begin();
        rightSections.zipWith(rightPositions, Button::new).forEach(this::drawButton);
        leftSections.zipWith(leftPositions, Button::new).forEach(this::drawButton);
        largeFont.draw(game.batch, currentSection, getViewportWidth()/2, getViewportHeight()/2 + 10);
        mediumFont.draw(game.batch, "Settings Screen", 30, 40);
        game.batch.end();
    }


    private void drawButton(Button button) {
        mediumFont.draw(game.batch, button.text, button.position.x, button.position.y);
    }

}

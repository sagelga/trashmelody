package com.trashmelody.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Inject;
import com.trashmelody.Assets;
import com.trashmelody.Debugger;
import com.trashmelody.TrashMelody;
import com.trashmelody.models.Button;
import com.trashmelody.models.Position;
import io.vavr.collection.List;

import static com.trashmelody.Constant.SCALE;
import static com.trashmelody.Constant.WIDTH;
import static com.trashmelody.Utils.*;
import static io.vavr.API.println;

public class SettingsScreen extends ScreenAdapter {
    private TrashMelody game;
    private SpriteBatch batch;
    private Camera camera;
    private Assets assets;
    private Viewport viewport;
    private List<String> rightSections;
    private List<Position> rightPositions;
    private List<String> leftSections;
    private List<Position> leftPositions;
    private String currentSection;
    private BitmapFont largeFont;
    private BitmapFont mediumFont;

    @Inject
    SettingsScreen(TrashMelody game, SpriteBatch batch, Camera camera, Assets assets, Viewport viewport) {
        this.game = game;
        this.batch = batch;
        this.camera = camera;
        this.assets = assets;
        this.viewport = viewport;

        largeFont = assets.getSuperSpaceFont((int)(40 * SCALE), Color.BLACK);
        mediumFont = assets.getSuperSpaceFont((int)(25 * SCALE), Color.BLACK);
        mediumFont.setUseIntegerPositions(false);
        largeFont.setUseIntegerPositions(false);

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
        clearScreen();

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !rightSections.isEmpty()) {
            leftSections = leftSections.prepend(currentSection);
            currentSection = rightSections.head();
            rightSections = rightSections.tail();
        }

        game.batch.begin();

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !leftSections.isEmpty()) {
            rightSections = rightSections.prepend(currentSection);
            currentSection = leftSections.head();
            leftSections = leftSections.tail();
        }

        batch.begin();
        rightSections.zipWith(rightPositions, Button::new).forEach(this::drawButton);
        leftSections.zipWith(leftPositions, Button::new).forEach(this::drawButton);
        largeFont.draw(batch, currentSection, getViewportWidth()/2, getViewportHeight()/2 + 10);
        mediumFont.draw(batch, "Settings Screen", 30, 40);

       // Debug zone
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Debugger.debug_mode = !Debugger.debug_mode;
        }
        if (Debugger.debug_mode){
            Debugger.runDebugger(game.batch, game.font,"Settings Screen");
            Debugger.runAdvancedDebugger(game.batch,game.font,0,0);
        }
        // Debug zone
        Debugger.runDebugger(batch, mediumFont, "Setting Screen");
        Debugger.runAdvancedDebugger(batch, mediumFont,0,0);
        Debugger.logScreenResolution();

        batch.end();
    }

    private void drawButton(Button button) {
        mediumFont.draw(batch, button.text, button.position.x, button.position.y);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        SCALE = getViewportWidth() / WIDTH;
        largeFont = assets.getSuperSpaceFont((int)(40 * SCALE), Color.BLACK);
        mediumFont = assets.getSuperSpaceFont((int)(25 * SCALE), Color.BLACK);
        viewport.update(width, height);
    }
}

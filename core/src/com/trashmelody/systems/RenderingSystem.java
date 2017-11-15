package com.trashmelody.systems;

import com.badlogic.gdx.graphics.Camera;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.TextureComponent;
import com.trashmelody.components.TextureRegionComponent;
import com.trashmelody.components.TransformComponent;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;
import io.vavr.control.Option;

import static com.trashmelody.constants.B2Dvars.PPM;

public class RenderingSystem extends IteratingSystem {
    private SpriteBatch batch;
    private OrthographicCamera camera;

    @Inject
    public RenderingSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(TransformComponent.class)
                .one(TextureComponent.class, TextureRegionComponent.class)
                .get()
        );
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mapper.transform.get(entity);
        TextureComponent textureComponent = Mapper.texture.get(entity);
        TextureRegionComponent textureRegionComponent = Mapper.textureRegion.get(entity);

        Vector2 position = transform.position;
        Option<Vector2> maybeSize = transform.size;
        float scale = transform.scale;

        if (textureComponent != null) {
            Texture texture = textureComponent.texture;
            Vector2 size = maybeSize.getOrElse(new Vector2(texture.getWidth(), texture.getHeight()));
            float width = size.x * scale;
            float height = size.y * scale;
            batch.draw(
                    texture,
                    position.x - width / 2F,
                    position.y - height / 2F,
                    width,
                    height
            );
        }

        if (textureRegionComponent != null) {
            TextureRegion img = textureRegionComponent.textureRegion;
            int imgWidth = img.getRegionWidth();
            int imgHeight = img.getRegionHeight();
            batch.draw(
                    img,
                    position.x - imgWidth / 2,
                    position.y - imgHeight / 2,
                    imgWidth / 2F,
                    imgHeight / 2F,
                    imgWidth,
                    imgHeight,
                    scale,
                    scale,
                    transform.angelRadiant * MathUtils.radiansToDegrees
            );
        }
    }

    @Override
    public void update(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }
}

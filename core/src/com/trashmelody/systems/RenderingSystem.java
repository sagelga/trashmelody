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
//        this.camera = new OrthographicCamera(1280 * 2 * 10 / PPM, 720 * 2 * 10/ PPM);
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mapper.transform.get(entity);
        Vector2 position = transformComponent.position;
        float scale = transformComponent.scale;

        System.out.println("inside rendering system");

        TextureComponent textureComponent = Mapper.texture.get(entity);
        if (textureComponent != null) {
            Texture img = textureComponent.texture;
            int imgWidth = img.getWidth();
            int imgHeight = img.getHeight();
            batch.draw(
                    img,
                    position.x - imgWidth / 32F / 2F * scale,
                    position.y - imgHeight / 32F / 2F * scale,
                    (imgWidth / 32F) * scale,
                    (imgHeight / 32F) * scale
            );

//            System.out.println(imgWidth / 32F);
//            System.out.println(imgHeight / 32F);
//            System.out.println(scale);
//            System.out.println((imgWidth / 32F) * scale);
        }
        TextureRegionComponent textureRegionComponent = Mapper.textureRegion.get(entity);
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
                    transformComponent.angelRadiant * MathUtils.radiansToDegrees
            );
//            batch.draw(
//                    img,
//                    position.x - imgWidth / 32F / 2F,
//                    position.y - imgHeight / 32F / 2F
//            );
//            System.out.println(position.x - imgWidth / 320F / 2F);
//            System.out.println(position.x);
//            System.out.println(position.y - imgHeight / 320F / 2F);
//            System.out.println(position.y);
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

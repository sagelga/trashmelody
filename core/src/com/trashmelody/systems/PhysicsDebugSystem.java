package com.trashmelody.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import static com.trashmelody.constants.B2Dvars.PPM;

public class PhysicsDebugSystem extends EntitySystem {
    private World world;
    private Camera camera;
    private Box2DDebugRenderer renderer = new Box2DDebugRenderer();

    @Inject
    public PhysicsDebugSystem(World world, @Named("physics") OrthographicCamera camera) {
        super(100);

        this.camera = camera;
//        this.camera = new OrthographicCamera(1280 * 2 / PPM, 720 * 2 / PPM);
        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        renderer.render(world, camera.combined);
    }
}

package com.trashmelody.systems;

import com.badlogic.ashley.core.EntitySystem;

import java.util.Arrays;
import java.util.List;

public class Systems {
    public static Systems systems = new Systems(Arrays.asList(
            CollisionSystem.class,
            RenderingSystem.class,
            PlayerControlSystem.class,
            PhysicsDebugSystem.class,
            PhysicsSystem.class,
            PhysicsSynchronizationSystem.class,
            MusicSynchronizeSystem.class,
            HitObjectSystem.class,
            ScanLineSystem.class,
            DispatchSystem.class,
            RemovingSystem.class,
            ControlSystem.class,
            AccuracySystem.class,
            ScoringSystem.class,
            RemovingSystem.class
    ));

    public List<Class<? extends EntitySystem>> list;

    private Systems(List<Class<? extends EntitySystem>> list) {
        this.list = list;
    }
}

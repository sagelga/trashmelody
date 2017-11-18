package com.trashmelody.systems;

import com.badlogic.ashley.core.EntitySystem;

import java.util.Arrays;
import java.util.List;

public class Systems {
    public static Systems systems = new Systems(Arrays.asList(
            DispatchSystem.class,
            ScanLineSystem.class,
            PlayerControlSystem.class,
            ControlSystem.class,
            HitObjectSystem.class,
            AccuracySystem.class,
            ScoringSystem.class,
            DestroySystem.class,
            PhysicsSystem.class,
            CollisionSystem.class,
            TimerSystem.class,
            PhysicsSynchronizationSystem.class,
            MusicSynchronizeSystem.class,
            RenderingSystem.class,
            PhysicsDebugSystem.class
    ));

    public static int getIndex(Class<? extends EntitySystem> system) {
        return systems.list.indexOf(system);
    }

    public List<Class<? extends EntitySystem>> list;

    private Systems(List<Class<? extends EntitySystem>> list) {
        this.list = list;
    }
}

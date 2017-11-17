package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.TimerComponent;

public class TimerSystem extends IteratingSystem {
    @Inject
    TimerSystem() {
        super(Family.all(TimerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TimerComponent timer = Mapper.timer.get(entity);

        timer.listener.handle(entity, timer.getLifeTime(), timer.remaining);
        timer.remaining -= deltaTime * 1000;

        if (timer.remaining <= 0) {
            timer.listener.done(entity);
            entity.remove(TimerComponent.class);
        }
    }
}

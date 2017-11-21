package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.CallbackComponent;
import com.trashmelody.components.Mapper;

public class CallbackSystem extends IteratingSystem {
    @Inject
    public CallbackSystem() {
        super(Family.all(CallbackComponent.class).get(), Systems.getIndex(CallbackSystem.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CallbackComponent callback = Mapper.callback.get(entity);

        if (callback.waitFor <= 0) {
            callback.listener.handle(entity);
            entity.remove(CallbackComponent.class);
        }
        callback.waitFor -= deltaTime * 1000;
    }
}

package com.trashmelody.components;

import com.badlogic.ashley.core.Entity;

public abstract class TimerListener {
    public void handle(Entity entity, float lifeTime, float remaining) {
    }

    public void done(Entity entity) {
    }
}

package com.trashmelody.components;

import com.badlogic.ashley.core.Entity;

public interface TimerListener {
    void handle(Entity entity, float lifeTime, float remaining, float delta);
}

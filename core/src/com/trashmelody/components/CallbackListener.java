package com.trashmelody.components;

import com.badlogic.ashley.core.Entity;

public interface CallbackListener {
    void handle(Entity entity);
}

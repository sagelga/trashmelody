package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;

public class RemovingComponent implements Component {
    private float maxLifeTime;
    public float lifeTime;

    public RemovingComponent(float lifeTime) {
        this.lifeTime = lifeTime;
        this.maxLifeTime = lifeTime;
    }

    public float getMaxLifeTime() {
        return maxLifeTime;
    }
}

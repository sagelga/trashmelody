package com.trashmelody.components;

import com.badlogic.ashley.core.Component;

public class TimerComponent implements Component {
    private float lifeTime;
    public float remaining;
    public TimerListener listener;

    public TimerComponent(TimerListener listener, float lifeTime) {
        this.listener = listener;
        this.remaining = lifeTime;
        this.lifeTime = lifeTime;
    }

    public float getLifeTime() {
        return lifeTime;
    }
}

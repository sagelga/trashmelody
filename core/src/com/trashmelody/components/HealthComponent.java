package com.trashmelody.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
    private float maxHealth;
    public float health;

    public HealthComponent(float maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}

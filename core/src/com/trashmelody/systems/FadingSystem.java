package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.RemovingComponent;
import com.trashmelody.components.TextureComponent;

public class FadingSystem extends IteratingSystem {
    @Inject
    public FadingSystem() {
        super(Family.all(RemovingComponent.class, TextureComponent.class).get(), Systems.getIndex(FadingSystem.class));
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RemovingComponent removing = Mapper.removing.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);

        System.out.println(removing.lifeTime / removing.getMaxLifeTime());
        texture.setAlpha(removing.lifeTime / removing.getMaxLifeTime());
    }
}

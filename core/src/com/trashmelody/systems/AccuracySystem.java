package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.entities.FallingTrash;
import com.trashmelody.screens.GameScreen;

import static com.trashmelody.components.ScoringComponent.Accuracy;
import static com.trashmelody.constants.Constants.*;

public class AccuracySystem extends IteratingSystem {

    private World world;

    @Inject
    public AccuracySystem(World world) {
        super(Family.all(ScoringComponent.class, HitObjectComponent.class).get(), Systems.getIndex(AccuracySystem.class));

        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoringComponent scoring = Mapper.scoring.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);

        Accuracy accuracy = getAccuracy(scoring.getTimingError());
        scoring.setAccuracy(accuracy);

        scoring.getClickedType().forEach(clickedType -> getEngine().addEntity(new FallingTrash(
            world,
            GameScreen.binPositionMapper.get(clickedType).get(),
            new TextureComponent(texture.texture),
            new TypeComponent(TypeComponent.ITEM)
        )));

        entity.remove(HitObjectComponent.class);
    }

    private Accuracy getAccuracy(float timingError) {
        if (isPerfect.test(timingError)) {
            return Accuracy.Perfect;
        } else if (isGood.test(timingError)) {
            return Accuracy.Good;
        } else if (isNice.test(timingError)) {
            return Accuracy.Nice;
        } else if (isBad.test(timingError)) {
            return Accuracy.Bad;
        } else {
            return Accuracy.Miss;
        }
    }
}

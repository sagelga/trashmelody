package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.google.inject.Inject;
import com.trashmelody.components.*;
import com.trashmelody.components.ScoringComponent.Accuracy;
import com.trashmelody.managers.Assets;

import static com.trashmelody.managers.Assets.*;

public class ScoringSystem extends IteratingSystem {
    Assets assets;
    private Texture perfect;
    private Texture good;
    private Texture cool;
    private Texture bad;
    private Texture miss;

    @Inject
    public ScoringSystem(Assets assets) {
        super(Family.all(ScoringComponent.class, TextureComponent.class).get(), 11);

        this.assets = assets;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ScoringComponent scoring = Mapper.scoring.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);
        Texture accuracyTexture = miss;

        if (scoring.getAccuracy() == Accuracy.Perfect) {
            accuracyTexture = assets.get(PERFECT_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Good) {
            accuracyTexture = assets.get(GOOD_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Cool) {
            accuracyTexture = assets.get(COOL_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Bad) {
            accuracyTexture = assets.get(BAD_ACCURACY);
        } else if (scoring.getAccuracy() == Accuracy.Miss) {
            accuracyTexture = assets.get(MISS_ACCURACY);
        }

        texture.texture = accuracyTexture;

        entity.remove(HitObjectComponent.class);
        entity.add(new RemovingComponent(1));
    }
}

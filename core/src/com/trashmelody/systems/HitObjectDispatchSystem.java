package com.trashmelody.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;
import com.trashmelody.components.HitObjectComponent;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.TypeComponent;
import com.trashmelody.entities.HitObjectEntity;
import com.trashmelody.managers.Assets;
import io.vavr.collection.Queue;
import io.vavr.collection.Stream;
import lt.ekgame.beatmap_analyzer.beatmap.Beatmap;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;
import lt.ekgame.beatmap_analyzer.beatmap.mania.ManiaBeatmap;
import lt.ekgame.beatmap_analyzer.parser.BeatmapException;
import lt.ekgame.beatmap_analyzer.parser.BeatmapParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Predicate;

import static com.badlogic.gdx.Input.Keys.*;

public class HitObjectDispatchSystem extends EntitySystem {
    private Beatmap beatmap;
    private Stream<HitObject> hitObjects;
    private Queue<HitObjectEntity> activeHitObjects = Queue.empty();
    private Engine engine;
    private World world;
    private Assets assets;
    private float elapsedTime = 0;

    @Inject
    public HitObjectDispatchSystem(Engine engine,
                                   World world,
                                   Assets assets) {
        this.engine = engine;
        this.world = world;
        this.assets = assets;

        beatmap = getBeatmap();
        hitObjects = Stream.ofAll(beatmap.getHitObjects());
    }

    @Override
    public void update(float deltaTime) {
        updateState(deltaTime);

        hitObjects.headOption().filter(isAfter(elapsedTime)).forEach(hitObject -> {
            HitObjectEntity hitObjectEntity = new HitObjectEntity(
                    world,
                    hitObject,
                    new HitObjectComponent(A, D, W, Q, 300F),
                    new TypeComponent(TypeComponent.PLAYER),
                    engine,
                    assets
            );
            engine.addEntity(hitObjectEntity);
            activeHitObjects = activeHitObjects.enqueue(hitObjectEntity);
            hitObjects = hitObjects.tail();
        });

        activeHitObjects
                .headOption()
                .filter(hitObjectEntity -> isAfter(elapsedTime - 2).test(hitObjectEntity.hitObject))
                .peek(engine::removeEntity)
                .peek(hitObjectEntity -> world.destroyBody(Mapper.physics.get(hitObjectEntity).body))
                .forEach(hitObjectEntity -> activeHitObjects = activeHitObjects.dequeue()._2);
    }

    private Predicate<HitObject> isAfter(float elapsedTime) {
        return hitObject -> hitObject.isAfterStartTime(elapsedTime * 1000);
    }

    private void updateState(float deltaTime) {
        elapsedTime += deltaTime;
    }

    private Beatmap getBeatmap() {
        BeatmapParser parser = new BeatmapParser();
        File file = new File("songs/Hitorigoto/ClariS - Hitorigoto -TV MIX- (Doormat) [Hard].osu");
        Beatmap beatmap = null;
        try {
            beatmap = parser.parse(file, ManiaBeatmap.class);
        } catch (BeatmapException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return beatmap;
    }
}

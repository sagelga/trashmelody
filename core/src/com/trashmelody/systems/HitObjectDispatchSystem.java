//package com.trashmelody.systems;
//
//import com.badlogic.ashley.core.Engine;
//import com.badlogic.ashley.core.EntitySystem;
//import com.badlogic.gdx.physics.box2d.World;
//import com.google.inject.Inject;
//import com.trashmelody.components.HitObjectComponent;
//import com.trashmelody.components.Mapper;
//import com.trashmelody.components.TextureComponent;
//import com.trashmelody.components.TypeComponent;
//import com.trashmelody.entities.HitObjectEntity;
//import com.trashmelody.managers.Assets;
//import io.vavr.collection.Queue;
//import io.vavr.collection.Stream;
//import lt.ekgame.beatmap_analyzer.beatmap.Beatmap;
//import lt.ekgame.beatmap_analyzer.beatmap.HitObject;
//import lt.ekgame.beatmap_analyzer.beatmap.mania.ManiaBeatmap;
//import lt.ekgame.beatmap_analyzer.parser.BeatmapException;
//import lt.ekgame.beatmap_analyzer.parser.BeatmapParser;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.function.Predicate;
//
//import static com.trashmelody.managers.Assets.CIGARETTE_HIT_OBJECT;
//import static com.trashmelody.managers.Assets.HITORIGOTO_HARD;
//import static com.trashmelody.managers.Assets.TEXTURE;
//
//public class HitObjectDispatchSystem extends EntitySystem {
//    private Beatmap beatmap;
//    private Stream<HitObject> hitObjects;
//    private Queue<HitObjectEntity> activeHitObjects = Queue.empty();
//    private Engine engine;
//    private World world;
//    private Assets assets;
//    private float elapsedTime = 0;
//
//    @Inject
//    public HitObjectDispatchSystem(Engine engine,
//                                   World world,
//                                   Assets assets) {
//        this.engine = engine;
//        this.world = world;
//        this.assets = assets;
//
//        hitObjects = Stream.ofAll(beatmap.getHitObjects());
//    }
//
//    @Override
//    public void update(float deltaTime) {
//        updateState(deltaTime);
//
//        hitObjects
//                .headOption()
//                .filter(isAfter(elapsedTime))
//                .map(hitObject -> new HitObjectEntity(
//                    world,
//                    new HitObjectComponent(hitObject),
//                    new TypeComponent(TypeComponent.DISPATCHER),
//                    new TextureComponent(assets.get(CIGARETTE_HIT_OBJECT, TEXTURE))
//                ))
//                .peek(hitObjectEntity -> {
//                    activeHitObjects = activeHitObjects.enqueue(hitObjectEntity);
//                    hitObjects = hitObjects.tail();
//                })
//                .forEach(engine::addEntity);
//
//        activeHitObjects
//                .headOption()
//                .map(Mapper.hitObject::get)
//                .filter(c -> isAfter(elapsedTime - 2).test(c.hitObject))
//                .peek(c -> c.status = HitObjectComponent.Status.Died)
//                .forEach(c -> activeHitObjects = activeHitObjects.dequeue()._2);
//
////                .peek(engine::removeEntity)
////                .peek(hitObjectEntity -> world.destroyBody(Mapper.physics.get(hitObjectEntity).body))
////                .forEach(hitObjectEntity -> activeHitObjects = activeHitObjects.dequeue()._2);
//    }
//
//    private Predicate<HitObject> isAfter(float elapsedTime) {
//        return hitObject -> hitObject.isAfterStartTime(elapsedTime * 1000);
//    }
//
//    private void updateState(float deltaTime) {
//        elapsedTime += deltaTime;
//    }
//}

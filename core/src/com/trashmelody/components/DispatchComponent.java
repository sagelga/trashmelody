package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import io.vavr.collection.Stream;
import lt.ekgame.beatmap_analyzer.beatmap.Beatmap;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

public class DispatchComponent implements Component {
    public Stream<HitObject> hitObjects;
    public float elapsedTime;
    public State state = State.Ready;
    public float velocity;

    public enum State {
        Ready, Playing, Pause, Stop
    }

    public DispatchComponent(Beatmap beatmap, float velocity) {
        this.hitObjects = Stream.ofAll(beatmap.getHitObjects());
        this.velocity = velocity;
    }
}

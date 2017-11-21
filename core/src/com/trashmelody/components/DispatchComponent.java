package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import io.vavr.collection.Stream;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.HitObject;

public class DispatchComponent implements Component {

    public State state = State.Ready;
    public float velocity;

    public enum State {
        Ready, Playing, Pause, Stop
    }

    public DispatchComponent(Beatmap beatmap, float velocity) {
        this.velocity = velocity;
    }

}

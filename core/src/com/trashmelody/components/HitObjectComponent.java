package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

public class HitObjectComponent implements Component {
    public HitObject hitObject;
    public State state = State.Alive;

    public enum State {
        Alive, Died
    }

    public HitObjectComponent(HitObject hitObject) {
        this.hitObject = hitObject;
    }
}

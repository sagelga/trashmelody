package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

public class HitObjectComponent implements Component {
    public HitObject hitObject;
    public Status status = Status.Alive;

    public enum Status {
        Alive, Died
    }

    public HitObjectComponent(HitObject hitObject) {
        this.hitObject = hitObject;
    }
}

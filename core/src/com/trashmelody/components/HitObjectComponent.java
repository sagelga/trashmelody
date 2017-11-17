package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;

public class HitObjectComponent implements Component {
    public HitObject hitObject;

    public HitObjectComponent(HitObject hitObject) {
        this.hitObject = hitObject;
    }
}

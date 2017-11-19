package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.trashmelody.beatmap.parser.beatmap.HitObject;

public class HitObjectComponent implements Component {
    public HitObject hitObject;

    public HitObjectComponent(HitObject hitObject) {
        this.hitObject = hitObject;
    }
}

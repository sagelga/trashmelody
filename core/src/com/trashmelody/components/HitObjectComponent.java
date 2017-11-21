package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.trashmelody.beatmap.parser.beatmap.HitObject;
import com.trashmelody.models.trashes.Trash;

public class HitObjectComponent implements Component {
    public HitObject hitObject;
    public Trash trash;

    public HitObjectComponent(HitObject hitObject, Trash trash) {
        this.hitObject = hitObject;
        this.trash = trash;
    }
}

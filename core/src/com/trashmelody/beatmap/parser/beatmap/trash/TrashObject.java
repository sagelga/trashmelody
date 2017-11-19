package com.trashmelody.beatmap.parser.beatmap.trash;

import com.trashmelody.beatmap.parser.beatmap.HitObject;
import com.trashmelody.beatmap.parser.beatmap.mania.ManiaObject;
import com.trashmelody.beatmap.parser.utils.Vector2;

public class TrashObject extends ManiaObject {
    public TrashObject(Vector2 position, int startTime, int endTime, int hitSound) {
        super(position, startTime, endTime, hitSound);
    }

    @Override
    public HitObject clone() {
        return new TrashObject(position.clone(), startTime, endTime, hitSound);
    }

    @Override
    public Type getType() {
        return null;
    }
}

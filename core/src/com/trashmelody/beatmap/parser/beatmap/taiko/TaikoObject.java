package com.trashmelody.beatmap.parser.beatmap.taiko;

import com.trashmelody.beatmap.parser.beatmap.HitObject;
import com.trashmelody.beatmap.parser.utils.Vector2;

public abstract class TaikoObject extends HitObject {

	public TaikoObject(Vector2 position, int startTime, int endTime, int hitSound) {
		super(position, startTime, endTime, hitSound);
	}
}

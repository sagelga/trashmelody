package com.trashmelody.beatmap.parser.beatmap.osu;

import com.trashmelody.beatmap.parser.beatmap.HitObject;
import com.trashmelody.beatmap.parser.utils.Vector2;

public abstract class OsuObject extends HitObject {
	
	protected boolean isNewCombo;

	public OsuObject(Vector2 position, int startTime, int endTime, int hitSound, boolean isNewCombo) {
		super(position, startTime, endTime, hitSound);
		this.isNewCombo = isNewCombo;
	}
	
	public boolean isNewCombo() {
		return isNewCombo;
	}
}

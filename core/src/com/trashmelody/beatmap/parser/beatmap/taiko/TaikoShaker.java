package com.trashmelody.beatmap.parser.beatmap.taiko;

import com.trashmelody.beatmap.parser.beatmap.HitObject;
import com.trashmelody.beatmap.parser.utils.Vector2;

public class TaikoShaker extends TaikoObject {

	public TaikoShaker(Vector2 position, int startTime, int endTime, int hitSound) {
		super(position, startTime, endTime, hitSound);
	}

	@Override
	public HitObject clone() {
		return new TaikoShaker(position.clone(), startTime, endTime, hitSound);
	}

	@Override
	public int getCombo() {
		return 0;
	}
}

package com.trashmelody.beatmap.parser.beatmap.ctb;

import com.trashmelody.beatmap.parser.utils.Vector2;

public class CatchFruit extends CatchObject {

	public CatchFruit(Vector2 position, int startTime, int hitSound, boolean isNewCombo) {
		super(position, startTime, startTime, hitSound, isNewCombo);
	}

	@Override
	public CatchObject clone() {
		return new CatchFruit(position.clone(), startTime, hitSound, isNewCombo);
	}
}

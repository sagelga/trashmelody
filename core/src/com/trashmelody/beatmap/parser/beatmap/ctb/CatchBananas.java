package com.trashmelody.beatmap.parser.beatmap.ctb;

import com.trashmelody.beatmap.parser.utils.Vector2;

public class CatchBananas extends CatchObject {

	public CatchBananas(Vector2 position, int startTime, int endTime, int hitSound) {
		super(position, startTime, endTime, hitSound, false);
	}

	@Override
	public CatchObject clone() {
		return new CatchBananas(position.clone(), startTime, endTime, hitSound);
	}

}

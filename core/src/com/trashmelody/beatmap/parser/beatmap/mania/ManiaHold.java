package com.trashmelody.beatmap.parser.beatmap.mania;

import com.trashmelody.beatmap.parser.utils.Vector2;

public class ManiaHold extends ManiaObject {
	public ManiaHold(Vector2 position, int startTime, int endTime, int hitSound) {
		super(position, startTime, endTime, hitSound);
	}

	@Override
	public Type getType() {
		return Type.Hold;
	}

	@Override
	public ManiaObject clone() {
		return new ManiaHold(position.clone(), startTime, endTime, hitSound);
	}

}

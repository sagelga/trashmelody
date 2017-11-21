package com.trashmelody.beatmap.parser.beatmap.mania;

import com.trashmelody.beatmap.parser.utils.Vector2;

public class ManiaSingle extends ManiaObject {

	public ManiaSingle(Vector2 position, int startTime, int hitSound) {
		super(position, startTime, startTime, hitSound);
	}

	@Override
	public Type getType() {
		return Type.Single;
	}

	@Override
	public ManiaObject clone() {
		return new ManiaSingle(position.clone(), startTime, hitSound);
	}
}

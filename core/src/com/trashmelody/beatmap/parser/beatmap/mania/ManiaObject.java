package com.trashmelody.beatmap.parser.beatmap.mania;

import com.trashmelody.beatmap.parser.beatmap.TimingPoint;
import com.trashmelody.beatmap.parser.utils.Vector2;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.HitObject;
import com.trashmelody.beatmap.parser.utils.MathUtils;

public abstract class ManiaObject extends HitObject {
	private int column;

	public enum Type {
		Single, Hold
	}

	public abstract Type getType();

	public ManiaObject(Vector2 position, int startTime, int endTime, int hitSound) {
		super(position, startTime, endTime, hitSound);
	}
	
	@Override
	public void finalize(TimingPoint current, TimingPoint parent, Beatmap beatmap) {
		int numColumns = ((ManiaBeatmap)beatmap).getCollumns();
		column = MathUtils.calculateManiaCollumn(position.getX(), numColumns);
	}
	
	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return String.valueOf(stringifyKeyValue("position", position)) +
				stringifyKeyValue("startTime", startTime) +
				stringifyKeyValue("endTime", endTime) +
				stringifyKeyValue("isHold", getType()) +
				stringifyKeyValue("hitSound", hitSound);
	}

	private <T> StringBuilder stringifyKeyValue(String key, T value) {
		StringBuilder builder = new StringBuilder();
		builder.append(key);
		builder.append(": ");
		builder.append(value);
		builder.append("\n");

		return builder;
	}
}

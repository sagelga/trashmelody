package lt.ekgame.beatmap_analyzer.beatmap.mania;

import lt.ekgame.beatmap_analyzer.beatmap.Beatmap;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;
import lt.ekgame.beatmap_analyzer.beatmap.TimingPoint;
import lt.ekgame.beatmap_analyzer.utils.MathUtils;
import lt.ekgame.beatmap_analyzer.utils.Vector2;

public abstract class ManiaObject extends HitObject {
	
	private int collumn;

	public ManiaObject(Vector2 position, int startTime, int endTime, int hitSound) {
		super(position, startTime, endTime, hitSound);
	}
	
	@Override
	public void finalize(TimingPoint current, TimingPoint parent, Beatmap beatmap) {
		int numCollumns = ((ManiaBeatmap)beatmap).getCollumns(); 
		collumn = MathUtils.calculateManiaCollumn(position.getX(), numCollumns);
	}
	
	public int getCollumn() {
		return collumn;
	}

	@Override
	public String toString() {
		return String.valueOf(stringifyKeyValue("position", position)) +
				stringifyKeyValue("startTime", startTime) +
				stringifyKeyValue("endTime", startTime) +
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

package lt.ekgame.beatmap_analyzer.beatmap.mania;

import lt.ekgame.beatmap_analyzer.beatmap.Beatmap;
import lt.ekgame.beatmap_analyzer.beatmap.HitObject;
import lt.ekgame.beatmap_analyzer.beatmap.TimingPoint;
import lt.ekgame.beatmap_analyzer.utils.MathUtils;
import lt.ekgame.beatmap_analyzer.utils.Vector2;

public abstract class ManiaObject extends HitObject {
	private int column;
	private boolean isHold;

	public ManiaObject(Vector2 position, int startTime, int endTime, int hitSound, boolean isHold) {
		super(position, startTime, endTime, hitSound);

		this.isHold = isHold;
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
				stringifyKeyValue("isHold", isHold) +
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

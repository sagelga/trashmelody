package lt.ekgame.beatmap_analyzer.beatmap.mania;

import lt.ekgame.beatmap_analyzer.utils.Vector2;

public class ManiaHold extends ManiaObject {

	public ManiaHold(Vector2 position, int startTime, int endTime, int hitSound) {
		super(position, startTime, endTime, hitSound, true);
	}

	@Override
	public ManiaObject clone() {
		return new ManiaHold(position.clone(), startTime, endTime, hitSound);
	}

}

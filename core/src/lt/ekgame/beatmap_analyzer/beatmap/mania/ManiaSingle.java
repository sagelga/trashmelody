package lt.ekgame.beatmap_analyzer.beatmap.mania;

import lt.ekgame.beatmap_analyzer.utils.Vector2;

public class ManiaSingle extends ManiaObject {

	public ManiaSingle(Vector2 position, int startTime, int hitSound) {
		super(position, startTime, startTime, hitSound);
	}

	@Override
	public ManiaObject clone() {
		return new ManiaSingle(position.clone(), startTime, hitSound);
	}

}

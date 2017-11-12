package lt.ekgame.beatmap_analyzer.beatmap.ctb;

import lt.ekgame.beatmap_analyzer.utils.Vector2;

public class CatchBananas extends CatchObject {

	public CatchBananas(Vector2 position, int startTime, int endTime, int hitSound) {
		super(position, startTime, endTime, hitSound, false);
	}

	@Override
	public CatchObject clone() {
		return new CatchBananas(position.clone(), startTime, endTime, hitSound);
	}

}

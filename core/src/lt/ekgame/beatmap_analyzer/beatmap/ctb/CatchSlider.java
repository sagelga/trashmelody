package lt.ekgame.beatmap_analyzer.beatmap.ctb;

import lt.ekgame.beatmap_analyzer.utils.Vector2;

public class CatchSlider extends CatchObject {

	public CatchSlider(Vector2 position, int startTime, int endTime, int hitSound, boolean isNewCombo) {
		super(position, startTime, endTime, hitSound, isNewCombo);
	}

	@Override
	public CatchObject clone() {
		return new CatchSlider(position.clone(), startTime, endTime, hitSound, isNewCombo);
	}

}

package lt.ekgame.beatmap_analyzer.beatmap.osu;

import lt.ekgame.beatmap_analyzer.utils.Vector2;

public class OsuSpinner extends OsuObject {
	
	public OsuSpinner(Vector2 position, int startTime, int endTime, int hitSound, boolean isNewCombo) {
		super(position, startTime, endTime, hitSound, isNewCombo);
	}

	@Override
	public OsuObject clone() {
		return new OsuSpinner(position.clone(), startTime, endTime, hitSound, isNewCombo);
	}
}

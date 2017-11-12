package lt.ekgame.beatmap_analyzer.beatmap;

import lt.ekgame.beatmap_analyzer.utils.Vector2;

public abstract class HitObject {

	protected Vector2 position;
	protected int startTime, endTime;
	protected int hitSound;
	protected boolean isNewCombo;
	
	public HitObject(Vector2 position, int startTime, int endTime, int hitSound) {
		this.position = position;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hitSound = hitSound;
	}
	
	/**
	 * Clones the object and every attribute it contains.
	 */
	public abstract HitObject clone();
	
	/**
	 * Finalize the object (calculate slider combo, mania key index, etc).
	 * @param current Current TimingPoint can be inherited.
	 * @param parent Current non-inherited TimingPoint.
	 * @param beatmap Betmap this object belongs to.
	 */
	public void finalize(TimingPoint current, TimingPoint parent, Beatmap beatmap) {
		// Do nothing by default
	}
	
	/**
	 * The max combo that can be achieved with this object.
	 * @return Integer value for the max combo.
	 */
	public int getCombo() {
		return 1;
	}

	/**
	 * The position of this object.
	 * @return Vector2 object.
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * Start time of the object.
	 * @return Integer value.
	 */
	public int getStartTime() {
		return startTime;
	}
	
	/**
	 * End time of the object. May be the same as the start time for some objects.
	 * @return Integer value.
	 */
	public int getEndTime() {
		return endTime;
	}

	public int getHitSound() {
		return hitSound;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public void setHitSound(int hitSound) {
		this.hitSound = hitSound;
	}

	public void setNewCombo(boolean isNewCombo) {
		this.isNewCombo = isNewCombo;
	}
}

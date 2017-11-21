package com.trashmelody.beatmap.parser.beatmap;

import com.trashmelody.beatmap.parser.parser.FilePart;
import com.trashmelody.beatmap.parser.parser.BeatmapException;
import com.trashmelody.beatmap.parser.parser.FilePartConfig;

public class BeatmapDifficulties {
	
	private double healthDrain;
	private double circleSize;
	private double overallDifficulty;
	private double approachRate;
	private double sliderMultiplier;
	private double tickRate;
	
	private BeatmapDifficulties() {}
	
	public BeatmapDifficulties(FilePart part) throws BeatmapException {
		FilePartConfig config = new FilePartConfig(part);
		healthDrain = config.getDouble("HPDrainRate");
		circleSize = config.getDouble("CircleSize");
		overallDifficulty = config.getDouble("OverallDifficulty");
		// Long ago, osu! OD and AR used to be the same setting, so some maps don't have it.
		approachRate = config.getDouble("ApproachRate", overallDifficulty);
		sliderMultiplier = config.getDouble("SliderMultiplier");
		tickRate = config.getDouble("SliderTickRate");
	}

	public BeatmapDifficulties clone() {
		BeatmapDifficulties clone = new BeatmapDifficulties();
		clone.healthDrain = this.healthDrain;
		clone.circleSize = this.circleSize;
		clone.overallDifficulty = this.overallDifficulty;
		clone.approachRate = this.approachRate;
		clone.sliderMultiplier = this.sliderMultiplier;
		clone.tickRate = this.tickRate;
		return clone;
	}

	public double getHealthPoint() {
		return healthDrain;
	}

	public double getCircleSize() {
		return circleSize;
	}

	public double getOverallDifficulty() {
		return overallDifficulty;
	}

	public double getApproachRate() {
		return approachRate;
	}

	public double getSliderMultiplier() {
		return sliderMultiplier;
	}

	public double getTickRate() {
		return tickRate;
	}

	public void setHealthPoint(double healthDrain) {
		this.healthDrain = healthDrain;
	}

	public void setCircleSize(double circleSize) {
		this.circleSize = circleSize;
	}

	public void getOverallDifficulty(double overallDifficulty) {
		this.overallDifficulty = overallDifficulty;
	}

	public void getApproachRate(double approachRate) {
		this.approachRate = approachRate;
	}

	public void setSliderMultiplier(double sliderMultiplier) {
		this.sliderMultiplier = sliderMultiplier;
	}

	public void setTickRate(double tickRate) {
		this.tickRate = tickRate;
	}
}

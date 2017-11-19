package com.trashmelody.beatmap.parser.difficulty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.trashmelody.beatmap.parser.beatmap.osu.OsuBeatmap;
import com.trashmelody.beatmap.parser.beatmap.osu.OsuCircle;
import com.trashmelody.beatmap.parser.performance.OsuPerformanceCalculator;
import com.trashmelody.beatmap.parser.performance.Performance;
import com.trashmelody.beatmap.parser.performance.scores.Score;
import com.trashmelody.beatmap.parser.utils.Mods;

public class OsuDifficulty extends Difficulty {
	
	private double aimDiff, speedDiff;
	
	private List<Double> aimStrains;
	private List<Double> speedStrains;
	
	public OsuDifficulty(OsuBeatmap beatmap, Mods mods, double starDiff, double aimDiff, double speedDiff, List<Double> aimStrains, List<Double> speedStrains) {
		super(beatmap, mods, starDiff, mergeStrains(aimStrains, speedStrains));
		this.aimDiff = aimDiff;
		this.speedDiff = speedDiff;
		this.aimStrains = aimStrains;
		this.speedStrains = speedStrains;
	}
	
	private static List<Double> mergeStrains(List<Double> aimStrains, List<Double> speedStrains) {
		List<Double> overall = new ArrayList<Double>();
		Iterator<Double> aimIterator = aimStrains.iterator();
		Iterator<Double> speedIterator = speedStrains.iterator();
		while (aimIterator.hasNext() && speedIterator.hasNext()) {
			Double aimStrain = aimIterator.next();
			Double speedStrain = speedIterator.next();
			overall.add(aimStrain + speedStrain + Math.abs(speedStrain - aimStrain)*OsuDifficultyCalculator.EXTREME_SCALING_FACTOR);
		}
		return overall;
	}
	
	public List<Double> getAimStrains() {
		return aimStrains;
	}
	
	public List<Double> getSpeedStrains() {
		return speedStrains;
	}

	public double getAim() {
		return aimDiff;
	}

	public double getSpeed() {
		return speedDiff;
	}
	
	public double getAR() {
		return ((OsuBeatmap)beatmap).getAR(mods);
	}

	public double getOD() {
		return ((OsuBeatmap)beatmap).getOD(mods);
	}
	
	public int getNumCircles() {
		return (int) ((OsuBeatmap)beatmap).getHitObjects().stream().filter(o->o instanceof OsuCircle).count();
	}

	@Override
	public Performance getPerformance(Score score) {
		return new OsuPerformanceCalculator().calculate(this, score);
	}
}

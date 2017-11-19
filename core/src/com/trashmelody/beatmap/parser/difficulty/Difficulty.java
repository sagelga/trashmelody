package com.trashmelody.beatmap.parser.difficulty;

import java.util.List;

import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.performance.Performance;
import com.trashmelody.beatmap.parser.performance.scores.Score;
import com.trashmelody.beatmap.parser.utils.Mod;
import com.trashmelody.beatmap.parser.utils.Mods;

public abstract class Difficulty {
	
	protected Beatmap beatmap;
	protected Mods mods;
	protected double starDiff;
	protected List<Double> strains;
	
	public Difficulty(Beatmap beatmap, Mods mods, double starDiff, List<Double> strains) {
		this.beatmap = beatmap;
		this.mods = mods;
		this.starDiff = starDiff;
		this.strains = strains;
	}
	
	public abstract Performance getPerformance(Score score);
	
	public List<Double> getStrains() {
		return strains;
	}
	
	public double getSpeedMultiplier() {
		return mods.getSpeedMultiplier();
	}
	
	public double getOD() {
		return beatmap.getDifficultySettings().getOverallDifficulty();
	}
	
	public Beatmap getBeatmap() {
		return beatmap;
	}
	
	public Mods getMods() {
		return mods;
	}

	public double getStars() {
		return starDiff;
	}

	public int getMaxCombo() {
		return beatmap.getMaxCombo();
	}
	
	public int getObjectCount() {
		return beatmap.getObjectCount();
	}
	
	public boolean hasMod(Mod mod) {
		return mods.has(mod);
	}
}

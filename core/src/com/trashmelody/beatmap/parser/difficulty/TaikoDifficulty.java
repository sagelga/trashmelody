package com.trashmelody.beatmap.parser.difficulty;

import java.util.List;

import com.trashmelody.beatmap.parser.beatmap.taiko.TaikoBeatmap;
import com.trashmelody.beatmap.parser.performance.Performance;
import com.trashmelody.beatmap.parser.performance.TaikoPerformanceCalculator;
import com.trashmelody.beatmap.parser.performance.scores.Score;
import com.trashmelody.beatmap.parser.utils.Mods;

public class TaikoDifficulty extends Difficulty {

	public TaikoDifficulty(TaikoBeatmap beatmap, Mods mods, double starDiff, List<Double> strains) {
		super(beatmap, mods, starDiff, strains);
	}

	@Override
	public Performance getPerformance(Score score) {
		return new TaikoPerformanceCalculator().calculate(this, score);
	}
}

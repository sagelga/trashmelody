package com.trashmelody.beatmap.parser.difficulty;

import java.util.List;

import com.trashmelody.beatmap.parser.beatmap.mania.ManiaBeatmap;
import com.trashmelody.beatmap.parser.performance.ManiaPerformanceCalculator;
import com.trashmelody.beatmap.parser.performance.Performance;
import com.trashmelody.beatmap.parser.performance.scores.Score;
import com.trashmelody.beatmap.parser.utils.Mods;

public class ManiaDifficulty extends Difficulty {

	public ManiaDifficulty(ManiaBeatmap beatmap, Mods mods, double starDiff, List<Double> strains) {
		super(beatmap, mods, starDiff, strains);
	}

	@Override
	public Performance getPerformance(Score score) {
		return new ManiaPerformanceCalculator().calculate(this, score);
	}
}

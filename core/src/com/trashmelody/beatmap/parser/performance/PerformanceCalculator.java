package com.trashmelody.beatmap.parser.performance;

import com.trashmelody.beatmap.parser.difficulty.Difficulty;
import com.trashmelody.beatmap.parser.performance.scores.Score;

public interface PerformanceCalculator {
	
	public Performance calculate(Difficulty difficulty, Score score);

}

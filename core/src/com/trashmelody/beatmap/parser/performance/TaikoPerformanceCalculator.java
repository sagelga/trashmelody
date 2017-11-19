package com.trashmelody.beatmap.parser.performance;

import com.trashmelody.beatmap.parser.GameMode;
import com.trashmelody.beatmap.parser.difficulty.Difficulty;
import com.trashmelody.beatmap.parser.difficulty.TaikoDifficulty;
import com.trashmelody.beatmap.parser.performance.scores.Score;
import com.trashmelody.beatmap.parser.utils.Mod;
import com.trashmelody.beatmap.parser.utils.MathUtils;

public class TaikoPerformanceCalculator implements PerformanceCalculator {
	
	@Override
	public Performance calculate(Difficulty difficulty, Score score) {
		TaikoDifficulty diff = (TaikoDifficulty) difficulty;
		double multiplier = 1.1;
		
		if (diff.hasMod(Mod.NO_FAIL))
			multiplier *= 0.9;
		
		if (diff.hasMod(Mod.SPUN_OUT))
			multiplier *= 0.95;
		
		if (diff.hasMod(Mod.HIDDEN))
			multiplier *= 1.1;
		
		double accuracy = score.getAccuracy();
		double strainValue = calculateStrainValue(diff, score);
		double accValue = calculateAccuracyValue(diff, score);
		double performance = Math.pow(Math.pow(strainValue, 1.1) + Math.pow(accValue, 1.1), 1/1.1)*multiplier;
		
		return new Performance(accuracy, performance, 0, strainValue, accValue);
	}
	
	private double calculateStrainValue(TaikoDifficulty difficulty, Score score) {
		double strainValue = Math.pow(5*Math.max(1, difficulty.getStars()/0.0075) - 4, 2)/100000;
		double lengthBonus = 1 + 0.1 * Math.min(1, difficulty.getObjectCount() / 1500.0);
		strainValue *= lengthBonus;
		
		// miss penalty
		strainValue *= Math.pow(0.985, score.getMisses());
		
		int maxCombo = difficulty.getMaxCombo();
		if (maxCombo > 0)
			strainValue *= Math.min(Math.pow(score.getCombo(), 0.5)/Math.pow(maxCombo, 0.5), 1);
		
		if (difficulty.hasMod(Mod.HIDDEN))
			strainValue *= 1.025;
		
		if (difficulty.hasMod(Mod.FLASHLIGHT))
			strainValue *= 1.05 * lengthBonus;
		
		return strainValue*score.getAccuracy();
	}
	
	private double calculateAccuracyValue(TaikoDifficulty difficulty, Score score) {
		int perfectHitWindow = MathUtils.getHitWindow300(difficulty.getOD(), GameMode.TAIKO, difficulty.getMods());
		if (perfectHitWindow <= 0)
			return 0;
		
		double accValue = Math.pow(150.0/perfectHitWindow, 1.1) * Math.pow(score.getAccuracy(), 15)*22;
		return accValue*Math.min(1.15, Math.pow(difficulty.getMaxCombo()/1500.0, 0.3));
	}
}

package com.trashmelody.beatmap.parser.difficulty;

import java.util.List;

import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.utils.Mods;

public interface DifficultyCalculator {
	
	public Difficulty calculate(Mods mods, Beatmap beatmap);
	
	public double calculateDifficulty(List<Double> strains);

}

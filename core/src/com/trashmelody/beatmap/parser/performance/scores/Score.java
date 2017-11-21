package com.trashmelody.beatmap.parser.performance.scores;

import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.utils.ScoreVersion;

public class Score {
	
	private int score, combo, numMiss;
	private double accuracy;
	private ScoreVersion version;
	
	Score(int score, int combo, double accuracy, int numMiss, ScoreVersion version) {
		this.score = score;
		this.combo = combo;
		this.accuracy = accuracy;
		this.numMiss = numMiss;
		this.version = version;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getCombo() {
		return combo;
	}

	public int getMisses() {
		return numMiss;
	}
	
	public double getAccuracy() {
		return accuracy;
	}
	
	public ScoreVersion getVersion() {
		return version;
	}

	public static ScoreBuilder of(Beatmap beatmap) {
		return new ScoreBuilder(beatmap);
	}
}

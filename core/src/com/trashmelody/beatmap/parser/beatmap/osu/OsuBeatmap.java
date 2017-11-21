package com.trashmelody.beatmap.parser.beatmap.osu;

import com.trashmelody.beatmap.parser.GameMode;
import com.trashmelody.beatmap.parser.beatmap.*;
import com.trashmelody.beatmap.parser.difficulty.OsuDifficulty;
import com.trashmelody.beatmap.parser.difficulty.OsuDifficultyCalculator;
import com.trashmelody.beatmap.parser.utils.MathUtils;
import com.trashmelody.beatmap.parser.utils.Mod;
import com.trashmelody.beatmap.parser.utils.Mods;

import java.nio.file.Path;
import java.util.List;

public class OsuBeatmap extends Beatmap {
	
	private List<OsuObject> hitObjects;

	public OsuBeatmap(BeatmapGenerals generals, BeatmapEditorState editorState, BeatmapMetadata metadata, BeatmapDifficulties difficulties,
					  List<BreakPeriod> breaks, List<TimingPoint> timingPoints, List<OsuObject> hitObjects, Path path)
	{
		super(generals, editorState, metadata, difficulties, breaks, timingPoints, path);
		this.hitObjects = hitObjects;
		
		finalizeObjects(hitObjects);
	}
	
	public double getOD() {
		return difficulties.getOverallDifficulty();
	}
	
	public double getOD(Mods mods) {
		if (!mods.isMapChanging())
			return getOD();
		
		double odMultiplier = 1;
		if (mods.has(Mod.HARDROCK)) odMultiplier *= 1.4;
		if (mods.has(Mod.EASY)) odMultiplier *= 0.5;
		return MathUtils.recalculateOverallDifficulty(getOD(), odMultiplier, mods.getSpeedMultiplier());
	}
	
	public double getAR() {
		return difficulties.getApproachRate();
	}
	
	public double getAR(Mods mods) {
		if (!mods.isMapChanging())
			return getAR();
		
		double arMultiplier = 1;
		if (mods.has(Mod.HARDROCK)) arMultiplier *= 1.4;
		if (mods.has(Mod.EASY)) arMultiplier *= 0.5;
		return MathUtils.recalculateApproachRate(getAR(), arMultiplier, mods.getSpeedMultiplier());
	}
	
	public double getCS() {
		return difficulties.getCircleSize();
	}
	
	public double getCS(Mods mods) {
		if (!mods.isMapChanging())
			return getCS();
		
		double csMultiplier = 1;
		if (mods.has(Mod.HARDROCK)) csMultiplier *= 1.3;
		if (mods.has(Mod.EASY)) csMultiplier *= 0.5;
		return MathUtils.recalculateCircleSize(getCS(), csMultiplier);
	}

	@Override
	public GameMode getGameMode() {
		return GameMode.OSU;
	}
	
	@Override
	public int getMaxCombo() {
		return hitObjects.stream().mapToInt(o->o.getCombo()).sum();
	}

	public List<OsuObject> getHitObjects() {
		return hitObjects;
	}
	
	@Override
	public int getObjectCount() {
		return hitObjects.size();
	}
	
	@Override
	public OsuDifficultyCalculator getDifficultyCalculator() {
		return new OsuDifficultyCalculator();
	}

	@Override
	public OsuDifficulty getDifficulty(Mods mods) {
		return getDifficultyCalculator().calculate(mods, this);
	}

	@Override
	public OsuDifficulty getDifficulty() {
		return getDifficulty(Mods.NOMOD);
	}
}

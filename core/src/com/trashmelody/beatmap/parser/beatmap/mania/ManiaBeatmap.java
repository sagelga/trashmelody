package com.trashmelody.beatmap.parser.beatmap.mania;

import java.nio.file.Path;
import java.util.List;

import com.trashmelody.beatmap.parser.GameMode;
import com.trashmelody.beatmap.parser.beatmap.TimingPoint;
import com.trashmelody.beatmap.parser.difficulty.ManiaDifficulty;
import com.trashmelody.beatmap.parser.difficulty.ManiaDifficultyCalculator;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.BeatmapDifficulties;
import com.trashmelody.beatmap.parser.beatmap.BeatmapEditorState;
import com.trashmelody.beatmap.parser.beatmap.BeatmapGenerals;
import com.trashmelody.beatmap.parser.beatmap.BeatmapMetadata;
import com.trashmelody.beatmap.parser.beatmap.BreakPeriod;
import com.trashmelody.beatmap.parser.utils.Mods;

public class ManiaBeatmap extends Beatmap {
	
	private List<ManiaObject> hitObjects;

	public ManiaBeatmap(BeatmapGenerals generals, BeatmapEditorState editorState, BeatmapMetadata metadata,
						BeatmapDifficulties difficulties, List<BreakPeriod> breaks, List<TimingPoint> timingPoints,
						List<ManiaObject> hitObjects, Path path) {
		super(generals, editorState, metadata, difficulties, breaks, timingPoints, path);
		this.hitObjects = hitObjects;
		
		finalizeObjects(hitObjects);
	}

	@Override
	public GameMode getGameMode() {
		return GameMode.MANIA;
	}
	
	@Override
	public ManiaDifficultyCalculator getDifficultyCalculator() {
		return new ManiaDifficultyCalculator();
	}

	@Override
	public ManiaDifficulty getDifficulty(Mods mods) {
		return getDifficultyCalculator().calculate(mods, this);
	}
	
	@Override
	public ManiaDifficulty getDifficulty() {
		return getDifficulty(Mods.NOMOD);
	}
	
	public List<ManiaObject> getHitObjects() {
		return hitObjects;
	}
	
	public int getCollumns() {
		return (int)difficulties.getCircleSize();
	}

	@Override
	public int getMaxCombo() {
		return hitObjects.stream().mapToInt(o->o.getCombo()).sum();
	}
	
	@Override
	public int getObjectCount() {
		return hitObjects.size();
	}
}

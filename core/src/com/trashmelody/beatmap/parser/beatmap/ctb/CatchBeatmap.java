package com.trashmelody.beatmap.parser.beatmap.ctb;

import java.nio.file.Path;
import java.util.List;

import com.trashmelody.beatmap.parser.GameMode;
import com.trashmelody.beatmap.parser.beatmap.BeatmapEditorState;
import com.trashmelody.beatmap.parser.beatmap.BeatmapMetadata;
import com.trashmelody.beatmap.parser.beatmap.BreakPeriod;
import com.trashmelody.beatmap.parser.beatmap.TimingPoint;
import com.trashmelody.beatmap.parser.difficulty.Difficulty;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.BeatmapDifficulties;
import com.trashmelody.beatmap.parser.beatmap.BeatmapGenerals;
import com.trashmelody.beatmap.parser.difficulty.DifficultyCalculator;
import com.trashmelody.beatmap.parser.utils.Mods;

public class CatchBeatmap  extends Beatmap {
	
	private List<CatchObject> hitObjects;

	public CatchBeatmap(BeatmapGenerals generals, BeatmapEditorState editorState, BeatmapMetadata metadata,
                        BeatmapDifficulties difficulties, List<BreakPeriod> breaks, List<TimingPoint> timingPoints,
                        List<CatchObject> hitObjects, Path path) {
		super(generals, editorState, metadata, difficulties, breaks, timingPoints, path);
		this.hitObjects = hitObjects;
		
		finalizeObjects(hitObjects);
	}
	
	public List<CatchObject> getHitObjects() {
		return hitObjects;
	}

	@Override
	public GameMode getGameMode() {
		return GameMode.CATCH;
	}

	@Override
	public Difficulty getDifficulty(Mods mods) {
		return null;
	}

	@Override
	public Difficulty getDifficulty() {
		return null;
	}
	
	@Override
	public DifficultyCalculator getDifficultyCalculator() {
		return null;
	}

	@Override
	public int getMaxCombo() {
		return 0;
	}

	@Override
	public int getObjectCount() {
		return 0;
	}
}

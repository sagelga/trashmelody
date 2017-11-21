package com.trashmelody.beatmap.parser.beatmap.taiko;

import java.nio.file.Path;
import java.util.List;

import com.trashmelody.beatmap.parser.GameMode;
import com.trashmelody.beatmap.parser.beatmap.*;
import com.trashmelody.beatmap.parser.difficulty.TaikoDifficulty;
import com.trashmelody.beatmap.parser.difficulty.TaikoDifficultyCalculator;
import com.trashmelody.beatmap.parser.utils.Mods;
import com.trashmelody.beatmap.parser.beatmap.*;

public class TaikoBeatmap extends Beatmap {
	
	private List<TaikoObject> hitObjects;

	public TaikoBeatmap(BeatmapGenerals generals, BeatmapEditorState editorState, BeatmapMetadata metadata,
						BeatmapDifficulties difficulties, List<BreakPeriod> breaks, List<TimingPoint> timingPoints,
						List<TaikoObject> hitObjects, Path path) {
		super(generals, editorState, metadata, difficulties, breaks, timingPoints, path);
		this.hitObjects = hitObjects;
		finalizeObjects(hitObjects);
	}

	@Override
	public GameMode getGameMode() {
		return GameMode.TAIKO;
	}

	@Override
	public int getMaxCombo() {
		return (int) hitObjects.stream().filter(o->o instanceof TaikoCircle).count();
	}
	
	public List<TaikoObject> getHitObjects() {
		return hitObjects;
	}

	@Override
	public int getObjectCount() {
		return hitObjects.size();
	}
	
	@Override
	public TaikoDifficultyCalculator getDifficultyCalculator() {
		return new TaikoDifficultyCalculator();
	}

	@Override
	public TaikoDifficulty getDifficulty(Mods mods) {
		return getDifficultyCalculator().calculate(mods, this);
	}

	@Override
	public TaikoDifficulty getDifficulty() {
		return getDifficulty(Mods.NOMOD);
	}
}

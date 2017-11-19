package com.trashmelody.beatmap.parser.parser.hitobjects;

import java.nio.file.Path;
import java.util.List;

import com.trashmelody.beatmap.parser.beatmap.BeatmapMetadata;
import com.trashmelody.beatmap.parser.beatmap.TimingPoint;
import com.trashmelody.beatmap.parser.beatmap.ctb.CatchObject;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.BeatmapDifficulties;
import com.trashmelody.beatmap.parser.beatmap.BeatmapEditorState;
import com.trashmelody.beatmap.parser.beatmap.BeatmapGenerals;
import com.trashmelody.beatmap.parser.beatmap.BreakPeriod;

public class CatchParser extends HitObjectParser<CatchObject> {

	@Override
	public CatchObject parse(String line) {
		return null;
	}

	@Override
	public Beatmap buildBeatmap(BeatmapGenerals generals, BeatmapEditorState editorState,
								BeatmapMetadata metadata, BeatmapDifficulties difficulties, List<BreakPeriod> breaks,
								List<TimingPoint> timingPoints, List<String> rawObjects, Path path) {
		return null;
	}
}

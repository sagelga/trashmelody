package com.trashmelody.beatmap.parser.parser.hitobjects;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.BeatmapDifficulties;
import com.trashmelody.beatmap.parser.beatmap.BeatmapEditorState;
import com.trashmelody.beatmap.parser.beatmap.BeatmapGenerals;
import com.trashmelody.beatmap.parser.beatmap.BeatmapMetadata;
import com.trashmelody.beatmap.parser.beatmap.BreakPeriod;
import com.trashmelody.beatmap.parser.beatmap.HitObject;
import com.trashmelody.beatmap.parser.beatmap.TimingPoint;

public abstract class HitObjectParser<T extends HitObject> {
	
	public abstract T parse(String line);
	
	public List<T> parse(List<String> lines) {
		return lines.stream().map(this::parse)
			.collect(Collectors.toList());
	}
	
	public abstract Beatmap buildBeatmap(BeatmapGenerals generals, BeatmapEditorState editorState,
										 BeatmapMetadata metadata, BeatmapDifficulties difficulties, List<BreakPeriod> breaks,
										 List<TimingPoint> timingPoints, List<String> rawObjects, Path path);
	
}

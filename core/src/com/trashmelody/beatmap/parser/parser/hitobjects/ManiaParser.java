package com.trashmelody.beatmap.parser.parser.hitobjects;

import java.nio.file.Path;
import java.util.List;

import com.trashmelody.beatmap.parser.beatmap.*;
import com.trashmelody.beatmap.parser.beatmap.mania.ManiaBeatmap;
import com.trashmelody.beatmap.parser.beatmap.mania.ManiaHold;
import com.trashmelody.beatmap.parser.beatmap.mania.ManiaObject;
import com.trashmelody.beatmap.parser.beatmap.mania.ManiaSingle;
import com.trashmelody.beatmap.parser.beatmap.*;
import com.trashmelody.beatmap.parser.beatmap.mania.*;
import com.trashmelody.beatmap.parser.utils.Vector2;

public class ManiaParser extends HitObjectParser<ManiaObject> {

	@Override
	public ManiaObject parse(String line) {
		String[] args = line.split(",");
		Vector2 position = new Vector2(
			Integer.parseInt(args[0].trim()),
			Integer.parseInt(args[1].trim())
		);
		int time = Integer.parseInt(args[2].trim());
		int type = Integer.parseInt(args[3].trim());
		int hitSound = Integer.parseInt(args[4].trim());
				
		if (true || (type & 3) > 0) {
			return new ManiaSingle(position, time, hitSound);
		}
		else {
			String[] additions = args[5].split(":");
			int endTime = Integer.parseInt(additions[0].trim());
			return new ManiaHold(position, time, endTime, hitSound);
		}
	}

	@Override
	public Beatmap buildBeatmap(BeatmapGenerals generals, BeatmapEditorState editorState, BeatmapMetadata metadata,
								BeatmapDifficulties difficulties, List<BreakPeriod> breaks, List<TimingPoint> timingPoints,
								List<String> rawObjects, Path path) {
		List<ManiaObject> hitObjects = parse(rawObjects);
		return new ManiaBeatmap(generals, editorState, metadata, difficulties, breaks, timingPoints, hitObjects, path);
	}
}

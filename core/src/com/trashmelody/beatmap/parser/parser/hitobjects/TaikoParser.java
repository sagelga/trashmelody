package com.trashmelody.beatmap.parser.parser.hitobjects;

import java.nio.file.Path;
import java.util.List;

import com.trashmelody.beatmap.parser.beatmap.*;
import com.trashmelody.beatmap.parser.beatmap.taiko.*;
import com.trashmelody.beatmap.parser.utils.Vector2;
import com.trashmelody.beatmap.parser.beatmap.*;
import com.trashmelody.beatmap.parser.beatmap.taiko.*;

public class TaikoParser extends HitObjectParser<TaikoObject> {

	@Override
	public TaikoObject parse(String line) {
		String[] args = line.split(",");
		Vector2 position = new Vector2(
			Integer.parseInt(args[0].trim()),
			Integer.parseInt(args[1].trim())
		);
		int time = Integer.parseInt(args[2].trim());
		int type = Integer.parseInt(args[3].trim());
		int hitSound = Integer.parseInt(args[4].trim());
		
		boolean isBlue = (hitSound & 2) > 0 || (hitSound & 8) > 0;
		TaikoCircle.TaikoColor color = isBlue ? TaikoCircle.TaikoColor.BLUE : TaikoCircle.TaikoColor.RED;
		boolean isBig = (hitSound & 4) > 0;
				
		if ((type & 1) > 0) {
			return new TaikoCircle(position, time, hitSound, color, isBig);
		}
		else if ((type & 2) > 0) {
			int repetitions = Integer.parseInt(args[6].trim());
			double pixelLength = Double.parseDouble(args[7].trim());
			return new TaikoDrumroll(position, time, hitSound, repetitions*pixelLength, isBig);
		}
		else {
			int endTime = Integer.parseInt(args[5].trim());
			return new TaikoShaker(position, time, endTime, hitSound);
		}
	}

	@Override
	public Beatmap buildBeatmap(BeatmapGenerals generals, BeatmapEditorState editorState,
								BeatmapMetadata metadata, BeatmapDifficulties difficulties, List<BreakPeriod> breaks,
								List<TimingPoint> timingPoints, List<String> rawObjects, Path path) {
		List<TaikoObject> hitObjects = parse(rawObjects);
		return new TaikoBeatmap(generals, editorState, metadata, difficulties, breaks, timingPoints, hitObjects, path);
	}

}

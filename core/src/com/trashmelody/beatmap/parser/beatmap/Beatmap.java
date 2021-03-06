package com.trashmelody.beatmap.parser.beatmap;

import java.nio.file.Path;
import java.util.List;
import java.util.ListIterator;

import com.trashmelody.beatmap.parser.GameMode;
import com.trashmelody.beatmap.parser.difficulty.Difficulty;
import com.trashmelody.beatmap.parser.difficulty.DifficultyCalculator;
import com.trashmelody.beatmap.parser.utils.Mods;

public abstract class Beatmap {
	
	protected BeatmapGenerals generals;
	protected BeatmapEditorState editorState;
	protected BeatmapMetadata metadata;
	protected BeatmapDifficulties difficulties;
	
	protected List<BreakPeriod> breaks;
	protected List<TimingPoint> timingPoints;

	private Path path;
	
	protected Beatmap(BeatmapGenerals generals, BeatmapEditorState editorState,
		BeatmapMetadata metadata, BeatmapDifficulties difficulties,
		List<BreakPeriod> breaks, List<TimingPoint> timingPoints, Path path)
	{
		this.generals = generals;
		this.editorState = editorState;
		this.metadata = metadata;
		this.difficulties = difficulties;
		this.breaks = breaks;
		this.timingPoints = timingPoints;
		this.path = path;
	}
	
	protected void finalizeObjects(List<? extends HitObject> objects) {
		ListIterator<TimingPoint> timingIterator = timingPoints.listIterator();
		ListIterator<? extends HitObject> objectIterator = objects.listIterator();
		
		// find first parent point
		TimingPoint parent = null;
		while (parent == null || parent.isInherited())
			parent = timingIterator.next();
		
		while (true) {
			TimingPoint current = timingIterator.hasNext() ? timingIterator.next() : null;
			TimingPoint previous = timingPoints.get(timingIterator.previousIndex() - (current == null ? 0 : 1));
			if (!previous.isInherited()) parent = previous;
			
			while (objectIterator.hasNext()) {
				HitObject object = objectIterator.next();
				if (current == null || object.getStartTime() < current.getTimestamp()) {
					object.finalize(previous, parent, this);
				}
				else {
					objectIterator.previous();
					break;
				}	
			}
			
			if (current == null) break;
		}
	}
	
	public abstract GameMode getGameMode();

	public abstract List<? extends HitObject> getHitObjects();

	public abstract DifficultyCalculator getDifficultyCalculator();
	
	public abstract Difficulty getDifficulty(Mods mods);
	
	public abstract Difficulty getDifficulty();
	
	public abstract int getMaxCombo();
	
	public abstract int getObjectCount();

	public BeatmapGenerals getGenerals() {
		return generals;
	}

	public BeatmapEditorState getEditorState() {
		return editorState;
	}

	public BeatmapMetadata getMetadata() {
		return metadata;
	}

	public BeatmapDifficulties getDifficultySettings() {
		return difficulties;
	}

	public List<BreakPeriod> getBreaks() {
		return breaks;
	}
	
	public List<TimingPoint> getTimingPoints() {
		return timingPoints;
	}

	public Path getPath() {
		return path;
	}

	public String getBeatmapSetId() {
		return metadata.getBeatmapSetId();
	}

	public String getBeatmapId() {
		return metadata.getBeatmapId();
	}

}

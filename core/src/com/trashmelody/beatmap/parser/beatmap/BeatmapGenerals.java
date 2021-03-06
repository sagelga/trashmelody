package com.trashmelody.beatmap.parser.beatmap;

import com.trashmelody.beatmap.parser.GameMode;
import com.trashmelody.beatmap.parser.parser.BeatmapException;
import com.trashmelody.beatmap.parser.parser.FilePart;
import com.trashmelody.beatmap.parser.parser.FilePartConfig;

public class BeatmapGenerals {

	private String audioFileName;
	private int audioLeadIn;
	private int previewTime;
	private boolean hasCountdown;
	private String sampleSet;
	private double stackLeniency;
	private GameMode gameMode;
	private boolean hasLetterboxing;
	private boolean hasEpilepsyWarning;
	private boolean hasWidescreenStoryboard;
	
	private BeatmapGenerals() {};
	
	public BeatmapGenerals(FilePart part) throws BeatmapException {
		FilePartConfig config = new FilePartConfig(part);
		audioFileName = config.getString("AudioFilename");
		audioLeadIn = config.getInt("AudioLeadIn", 0);
		previewTime = config.getInt("PreviewTime");
		hasCountdown = config.getBoolean("Countdown", true);
		sampleSet = config.getString("SampleSet");
		stackLeniency = config.getDouble("StackLeniency", 0.7);
		gameMode = config.getGameMode("Mode", GameMode.OSU);
		hasLetterboxing = config.getBoolean("LetterboxInBreaks", true);
		hasEpilepsyWarning = config.getBoolean("EpilepsyWarning", false);
		hasWidescreenStoryboard = config.getBoolean("WidescreenStoryboard", false);
	}
	
	public BeatmapGenerals clone() {
		BeatmapGenerals clone = new BeatmapGenerals();
		clone.audioFileName = this.audioFileName;
		clone.audioLeadIn = this.audioLeadIn;
		clone.previewTime = this.previewTime;
		clone.hasCountdown = this.hasCountdown;
		clone.sampleSet = this.sampleSet;
		clone.stackLeniency = this.stackLeniency;
		clone.gameMode = this.gameMode;
		clone.hasLetterboxing = this.hasLetterboxing;
		clone.hasEpilepsyWarning = this.hasEpilepsyWarning;
		clone.hasWidescreenStoryboard = this.hasWidescreenStoryboard;
		return clone;
	}

	public String getAudioFileName() {
		return audioFileName;
	}

	public int getAudioLeadIn() {
		return audioLeadIn;
	}

	public int getPreviewTime() {
		return previewTime;
	}

	public boolean hasCountdown() {
		return hasCountdown;
	}

	public String getSampleSet() {
		return sampleSet;
	}

	public double getStackLeniency() {
		return stackLeniency;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public boolean hasLetterboxing() {
		return hasLetterboxing;
	}

	public boolean hasEpilepsyWarning() {
		return hasEpilepsyWarning;
	}

	public boolean hasWidescreenStoryboard() {
		return hasWidescreenStoryboard;
	}

	public void setAudioFileName(String audioFileName) {
		this.audioFileName = audioFileName;
	}

	public void setAudioLeadIn(int audioLeadIn) {
		this.audioLeadIn = audioLeadIn;
	}

	public void setPreviewTime(int previewTime) {
		this.previewTime = previewTime;
	}

	public void setHasCountdown(boolean hasCountdown) {
		this.hasCountdown = hasCountdown;
	}

	public void setSampleSet(String sampleSet) {
		this.sampleSet = sampleSet;
	}

	public void setStackLeniency(double stackLeniency) {
		this.stackLeniency = stackLeniency;
	}

	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	public void setLetterboxing(boolean hasLetterboxing) {
		this.hasLetterboxing = hasLetterboxing;
	}

	public void setEpilepsyWarning(boolean hasEpilepsyWarning) {
		this.hasEpilepsyWarning = hasEpilepsyWarning;
	}

	public void setWidescreenStoryboard(boolean hasWidescreenStoryboard) {
		this.hasWidescreenStoryboard = hasWidescreenStoryboard;
	}
}

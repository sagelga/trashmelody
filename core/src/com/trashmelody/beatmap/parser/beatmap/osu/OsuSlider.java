package com.trashmelody.beatmap.parser.beatmap.osu;

import java.util.List;
import java.util.stream.Collectors;

import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.TimingPoint;
import com.trashmelody.beatmap.parser.utils.Vector2;

public class OsuSlider extends OsuObject {
	
	private SliderType sliderType;
	private List<Vector2> sliderPoints;
	private int repetitions;
	private double pixelLength;
	private int combo;

	public OsuSlider(Vector2 position, int startTime, int hitSound, boolean isNewCombo, SliderType sliderType, List<Vector2> sliderPoints, int repetitions, double pixelLength) {
		super(position, startTime, startTime, hitSound, isNewCombo);
		this.sliderType = sliderType;
		this.sliderPoints = sliderPoints;
		this.repetitions = repetitions;
		this.pixelLength = pixelLength;
	}
	
	@Override
	public OsuObject clone() {
		return new OsuSlider(position.clone(), startTime, hitSound, isNewCombo, sliderType, cloneSliderPoints(), repetitions, pixelLength);
	}
	
	@Override
	public void finalize(TimingPoint current, TimingPoint parent, Beatmap beatmap) {
		double velocityMultiplier = 1;
		if (current.isInherited() && current.getBeatLength() < 0)
			velocityMultiplier = -100/current.getBeatLength();
		
		double pixelsPerBeat = beatmap.getDifficultySettings().getSliderMultiplier()*100*velocityMultiplier;
		double beats = pixelLength*repetitions/pixelsPerBeat;
		int duration = (int) Math.ceil(beats*parent.getBeatLength());
		endTime = startTime + duration;
		
		combo = (int)Math.ceil((beats - 0.01)/repetitions*beatmap.getDifficultySettings().getTickRate()) - 1;
		combo *= repetitions;
		combo += repetitions + 1; // head and tail
	}
	
	@Override
	public int getCombo() {
		return combo;
	}
	
	private List<Vector2> cloneSliderPoints() {
		return sliderPoints.stream().map(o->o.clone()).collect(Collectors.toList());
	}
	
	public SliderType getSliderType() {
		return sliderType;
	}

	public List<Vector2> getSliderPoints() {
		return sliderPoints;
	}

	public int getRepetitions() {
		return repetitions;
	}

	public double getPixelLength() {
		return pixelLength;
	}

	public enum SliderType {
		LINEAR, ARC, BAZIER, CATMULL, INVALID;
		
		public static SliderType fromChar(char c) {
			switch (c) {
				case 'L': return LINEAR;
				case 'P': return ARC;
				case 'B': return BAZIER;
				case 'C': return CATMULL;
			}
			return INVALID;
		}
	}
}

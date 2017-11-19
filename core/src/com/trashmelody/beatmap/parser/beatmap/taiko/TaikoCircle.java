package com.trashmelody.beatmap.parser.beatmap.taiko;

import com.trashmelody.beatmap.parser.utils.Vector2;

public class TaikoCircle extends TaikoObject {
	
	private TaikoColor color;
	private boolean isBig;

	public TaikoCircle(Vector2 position, int startTime, int hitSound, TaikoColor color, boolean isBig) {
		super(position, startTime, startTime, hitSound);
		this.color = color;
		this.isBig = isBig;
	}

	@Override
	public TaikoObject clone() {
		return new TaikoCircle(position.clone(), startTime, hitSound, color, isBig);
	}
	
	public TaikoColor getColor() {
		return color;
	}
	
	public boolean isBig() {
		return isBig;
	}

	public enum TaikoColor {
		RED, BLUE
	}
}

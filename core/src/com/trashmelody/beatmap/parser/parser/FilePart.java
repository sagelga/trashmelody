package com.trashmelody.beatmap.parser.parser;

import java.util.List;

public class FilePart {

	private String tag;
	private List<String> lines;
	
	FilePart(String tag, List<String> lines) {
		this.tag = tag;
		this.lines = lines;
	}
	
	public String getTag() {
		return tag;
	}

	public List<String> getLines() {
		return lines;
	}
}

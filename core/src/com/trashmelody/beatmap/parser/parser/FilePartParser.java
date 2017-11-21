package com.trashmelody.beatmap.parser.parser;

public abstract class FilePartParser<T> {
	
	protected abstract T parseLine(String line);
	
	

}

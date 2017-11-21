package com.trashmelody.beatmap.parser.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.trashmelody.beatmap.parser.GameMode;
import com.trashmelody.beatmap.parser.beatmap.*;
import com.trashmelody.beatmap.parser.beatmap.mania.ManiaBeatmap;
import com.trashmelody.beatmap.parser.parser.hitobjects.*;
import com.trashmelody.beatmap.parser.GameModeMapper;
import com.trashmelody.beatmap.parser.beatmap.*;
import com.trashmelody.beatmap.parser.parser.hitobjects.*;
import io.vavr.control.Option;

public class BeatmapParser {
	
	private static final Pattern PART_TAG = Pattern.compile("^\\[(\\w+)\\]");
	private static final String[] REQUIRED_TAGS = {"General", "Metadata", "TimingPoints", "Difficulty", "Events", "HitObjects"};
	
	private static final Map<GameMode, HitObjectParser<?>> PARSERS = new HashMap<>();
	
	static {
		PARSERS.put(GameMode.OSU,   new OsuParser());
		PARSERS.put(GameMode.TAIKO, new TaikoParser());
		PARSERS.put(GameMode.CATCH, new CatchParser());
		PARSERS.put(GameMode.MANIA, new ManiaParser());
	}

	public <T extends Beatmap> Option<T> parseOption(Path path, Class<T> beatmapClass) {
		try {
			return Option.some(parse(path, beatmapClass));
		} catch (BeatmapException | FileNotFoundException e) {
			return Option.none();
		}
	}

	public Option<ManiaBeatmap> parseManiaBeatmap(Path path) {
		return parseOption(path, ManiaBeatmap.class);
	}

	public Beatmap parse(Path path) throws FileNotFoundException, BeatmapException {
		return parse(path, null);
	}

//	@SuppressWarnings("unchecked")
//	public <T extends Beatmap> Beatmap parse(String string, Class<T> beatmapClass) throws BeatmapException {
//		return parse(new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8)), beatmapClass);
//	}
	
	@SuppressWarnings("unchecked")
	public <T extends Beatmap> T parse(Path path, Class<T> beatmapClass) throws BeatmapException, FileNotFoundException {
		InputStream stream = new FileInputStream(path.toFile());
		try (Scanner scanner = new Scanner(stream)){
			Map<String, FilePart> parts = new HashMap<>();
			
			String tag = "Header";
			List<String> lines = new ArrayList<>();
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				Matcher matcher = PART_TAG.matcher(line);
				if (matcher.find()) {
					parts.put(tag, new FilePart(tag, lines));
					lines = new ArrayList<>();
					tag = matcher.group(1);
				}
				else if (!line.isEmpty() && !line.startsWith("//")){
					lines.add(line);
				}
			}
			parts.put(tag, new FilePart(tag, lines));
			
			for (String requiredTag : REQUIRED_TAGS)
				if (!parts.containsKey(requiredTag))
					throw new BeatmapException("Couldn't find required \"" + requiredTag + "\" tag found.");
			
			BeatmapGenerals generalSettings = new BeatmapGenerals(parts.get("General"));
			GameMode gameMode = GameModeMapper.get(beatmapClass);
			if (gameMode == GameMode.UNKNOWN) {
				gameMode = generalSettings.getGameMode();
			}
			HitObjectParser<?> parser = PARSERS.get(gameMode);
			// TODO: parse other game modes
			if (parser == null)
				return null;
			
			BeatmapMetadata metadata = new BeatmapMetadata(parts.get("Metadata"));
			BeatmapDifficulties difficulties = new BeatmapDifficulties(parts.get("Difficulty"));
			BeatmapEditorState editorState = null;
			
			// Older formats don't have the "Editor" tag
			if (parts.containsKey("Editor"))
				editorState = new BeatmapEditorState(parts.get("Editor"));
			
			List<BreakPeriod> breaks = parseBreaks(parts.get("Events"));
			List<TimingPoint> timingPoints = parseTimePoints(parts.get("TimingPoints"));
			List<String> rawObjects = parts.get("HitObjects").getLines();
			
			return (T) parser.buildBeatmap(generalSettings, editorState, metadata, difficulties, breaks, timingPoints, rawObjects, path);
		}
	}
	
	private static Function<String, TimingPoint> timingPointMapper = line -> {
		String[] args = line.split(",");
		
		double timestamp = Double.parseDouble(args[0].trim());
		double beatLength = Double.parseDouble(args[1].trim());
		int meter = 4;
		int sampleType = 0;
		int sampleSet = 0;
		int volume = 100;
		boolean isInherited = false;
		boolean isKiai = false;
		
		if (args.length > 2) {
			meter = Integer.parseInt(args[2].trim());
			sampleType = Integer.parseInt(args[3].trim());
			sampleSet = Integer.parseInt(args[4].trim());
			volume = Integer.parseInt(args[5].trim());
		}
		
		if (args.length >= 7)
			isInherited = Integer.parseInt(args[6].trim()) == 0;
		if (args.length >= 8)
			isKiai = Integer.parseInt(args[7].trim()) == 0;
		
		return new TimingPoint(timestamp, beatLength, meter, sampleType, sampleSet, volume, isInherited, isKiai);
	};
	
	private List<TimingPoint> parseTimePoints(FilePart part) {
		return part.getLines().stream()
			.map(timingPointMapper)
			.sorted((o1, o2) -> (int)(o1.getTimestamp()- o2.getTimestamp()))
			.collect(Collectors.toList());
	}
	
	private static Function<String, BreakPeriod> breakPeriodMapper = line -> {
		String[] args = line.split(",");
		return new BreakPeriod(
			Integer.parseInt(args[1].trim()),
			Integer.parseInt(args[2].trim())
		);
	};
	
	private List<BreakPeriod> parseBreaks(FilePart part) {
		return part.getLines().stream()
			.filter(o->o.trim().startsWith("2,"))
			.map(breakPeriodMapper)
			.collect(Collectors.toList());
	}
}

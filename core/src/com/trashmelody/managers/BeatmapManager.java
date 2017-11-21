package com.trashmelody.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.beatmap.parser.BeatmapGroup;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.BeatmapMetadata;
import com.trashmelody.beatmap.parser.parser.BeatmapParser;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import io.vavr.control.Option;

import java.io.IOException;
import java.nio.file.*;

@Singleton
public class BeatmapManager {

    private static final BeatmapParser parser = new BeatmapParser();
    private static final Path beatmapPath = Paths.get("beatmaps");
    private static final Path homeDirectory = Paths.get(System.getProperty("user.home"));
    private static final PathMatcher osuExtension = FileSystems.getDefault().getPathMatcher("glob:**/*.osu");
    private static Option<Map<String, Stream<Beatmap>>> beatmapsGroupedById = Option.none();

    @Inject
    public BeatmapManager() {
    }

    public static void main(String[] args) {
        BeatmapManager manager = new BeatmapManager();
        manager
            .getBeatmapsByGroupId()
            .mapValues(beatmaps -> beatmaps
                    .map(Beatmap::getMetadata)
                    .map(BeatmapMetadata::getTitleRomanized))
            .forEach(System.out::println);
    }

    public Set<BeatmapGroup> getBeatmapGroups() {
        Map<String, Stream<Beatmap>> beatmaps = getBeatmapsByGroupId();

        return beatmaps.keySet().map(key -> new BeatmapGroup(key, beatmaps.get(key).get()));
    }

    public Map<String, Stream<Beatmap>> getBeatmapsByGroupId() {
        return beatmapsGroupedById.getOrElse(getBeatmaps().groupBy(Beatmap::getBeatmapSetId));
    }

    private Stream<Beatmap> getBeatmaps() {
        return getOsuFilesFromAssets().flatMap(parser::parseManiaBeatmap);
    }

    private Stream<Path> getOsuFiles() {
        try {
            return Stream.ofAll(Files.walk(homeDirectory.resolve(beatmapPath))).filter(osuExtension::matches);
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    private Stream<Path> getOsuFilesFromAssets() {
        try {
            return Stream.ofAll(Files.walk(beatmapPath)).filter(osuExtension::matches);
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

}

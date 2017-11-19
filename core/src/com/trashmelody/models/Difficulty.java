package com.trashmelody.models;

import com.trashmelody.beatmap.parser.beatmap.mania.ManiaBeatmap;
import com.trashmelody.beatmap.parser.parser.BeatmapParser;
import io.vavr.control.Option;

import java.nio.file.Path;

public class Difficulty {

    private Path path;
    private Option<ManiaBeatmap> maniaBeatmap;
    private BeatmapParser parser = new BeatmapParser();

    public Difficulty(Path path) {
        this.path = path;
        this.maniaBeatmap = loadManiaBeatmap();
    }

    public Path getPath() {
        return path;
    }

    public Option<ManiaBeatmap> getManiaBeatmap() {
        return maniaBeatmap;
    }

    private Option<ManiaBeatmap> loadManiaBeatmap() {
        return parser.parseOption(path, ManiaBeatmap.class);
    }

}

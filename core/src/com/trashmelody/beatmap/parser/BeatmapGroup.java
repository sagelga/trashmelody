package com.trashmelody.beatmap.parser;

import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import io.vavr.collection.Stream;

public class BeatmapGroup {
    private String groupId;

    public BeatmapGroup(String groupId, Stream<Beatmap> beatmaps) {
        this.groupId = groupId;

    }
}

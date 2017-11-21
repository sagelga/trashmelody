package com.trashmelody.beatmap.parser;

import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.ctb.CatchBeatmap;
import com.trashmelody.beatmap.parser.beatmap.mania.ManiaBeatmap;
import com.trashmelody.beatmap.parser.beatmap.osu.OsuBeatmap;
import com.trashmelody.beatmap.parser.beatmap.taiko.TaikoBeatmap;

import java.util.HashMap;
import java.util.Map;

public class GameModeMapper {
    private static final Map<Class<? extends Beatmap>, GameMode> MAPPER = new HashMap<>();

    static {
        MAPPER.put(null, GameMode.UNKNOWN);
        MAPPER.put(OsuBeatmap.class, GameMode.OSU);
        MAPPER.put(TaikoBeatmap.class, GameMode.TAIKO);
        MAPPER.put(CatchBeatmap.class, GameMode.CATCH);
        MAPPER.put(ManiaBeatmap.class, GameMode.MANIA);
    }

    public static GameMode get(Class<? extends Beatmap> beatMapClass) {
        return MAPPER.get(beatMapClass);
    }
}

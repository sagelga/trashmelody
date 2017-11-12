package lt.ekgame.beatmap_analyzer;

import lt.ekgame.beatmap_analyzer.beatmap.Beatmap;
import lt.ekgame.beatmap_analyzer.beatmap.ctb.CatchBeatmap;
import lt.ekgame.beatmap_analyzer.beatmap.mania.ManiaBeatmap;
import lt.ekgame.beatmap_analyzer.beatmap.osu.OsuBeatmap;
import lt.ekgame.beatmap_analyzer.beatmap.taiko.TaikoBeatmap;

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

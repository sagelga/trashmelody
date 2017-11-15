package lt.ekgame.beatmap_analyzer.beatmap.trash;

import lt.ekgame.beatmap_analyzer.beatmap.HitObject;
import lt.ekgame.beatmap_analyzer.beatmap.mania.ManiaObject;
import lt.ekgame.beatmap_analyzer.utils.Vector2;

public class TrashObject extends ManiaObject {
    public TrashObject(Vector2 position, int startTime, int endTime, int hitSound) {
        super(position, startTime, endTime, hitSound);
    }

    @Override
    public HitObject clone() {
        return new TrashObject(position.clone(), startTime, endTime, hitSound);
    }

    @Override
    public Type getType() {
        return null;
    }
}

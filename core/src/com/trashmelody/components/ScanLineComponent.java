package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Music;
import com.trashmelody.beatmap.parser.beatmap.Beatmap;
import com.trashmelody.beatmap.parser.beatmap.HitObject;
import com.trashmelody.constants.Constants;
import com.trashmelody.entities.HitObjectEntity;
import com.trashmelody.models.Score;
import io.vavr.collection.Queue;
import io.vavr.collection.Stream;

public class ScanLineComponent implements Component {
    public float delay = Constants.PRE_DISPATCH_TIME;
    public float velocity;
    public float elapsedTime = -delay;
    public float endTime;
    public Score score = new Score();
    public Music music;
    public State state = State.Ready;
    public Queue<HitObjectEntity> activeHitObjects = Queue.empty();
    public Beatmap beatmap;
    public Stream<HitObject> hitObjects;


    public enum State {
        Ready, Playing, Pause, Stop
    }

    public ScanLineComponent(Music music, Beatmap beatmap, float velocity) {
        this.music = music;
        this.velocity = velocity;
        this.beatmap = beatmap;
        this.hitObjects = Stream.ofAll(beatmap.getHitObjects());
    }
}

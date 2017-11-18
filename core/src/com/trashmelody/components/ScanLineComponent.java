package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Music;
import com.trashmelody.constants.Constants;
import com.trashmelody.entities.HitObjectEntity;
import io.vavr.collection.Queue;

public class ScanLineComponent implements Component {
    private float delay = Constants.PRE_DISPATCH_TIME;
    public Direction direction = Direction.Right;
    public float elapsedTime = -delay;
    public Music music;
    public State state = State.Ready;
    public float velocity;
    public Queue<HitObjectEntity> activeHitObjects = Queue.empty();

    public enum State {
        Ready, Playing, Pause, Stop
    }

    public enum Direction {
        Left, Right
    }

    public ScanLineComponent(Music music, float velocity) {
        this.music = music;
        this.velocity = velocity;
    }
}

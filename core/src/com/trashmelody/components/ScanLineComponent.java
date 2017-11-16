package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Music;

public class ScanLineComponent implements Component {
    public float delay = 1000;
    public Direction direction = Direction.Right;
    public float speed;
    public float elapsedTime = -delay;
    public Music music;
    public State state = State.Ready;
    public float velocity;

    public enum State {
        Ready, Playing, Pause, Stop
    }

    public enum Direction {
        Left, Right
    }

    public ScanLineComponent(Music music, float speed, float velocity) {
        this.speed = speed;
        this.music = music;
        this.velocity = velocity;
    }
}

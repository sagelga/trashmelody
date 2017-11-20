package com.trashmelody.components;

import com.badlogic.ashley.core.Component;

public class ScoringComponent implements Component {
    /* Positive if clicked too early
    *  Negative if clicked too late */
    private float timingError;
    private Accuracy accuracy;

    public enum Accuracy {
        Perfect, Good, Nice, Bad, Miss
    }

    public ScoringComponent(float timingError) {
        this.timingError = timingError;
    }

    public float getTimingError() {
        return timingError;
    }

    public void setTimingError(float timingError) {
        this.timingError = timingError;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }
}

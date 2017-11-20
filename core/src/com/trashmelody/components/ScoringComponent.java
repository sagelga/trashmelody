package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.trashmelody.models.trashes.TrashType;
import io.vavr.control.Option;

public class ScoringComponent implements Component {
    /* Positive if clicked too early
    *  Negative if clicked too late */
    private float timingError;
    private Accuracy accuracy;
    private Option<TrashType> clickedType;

    public enum Accuracy {
        Perfect, Good, Nice, Bad, Miss
    }

    public ScoringComponent(float timingError, Option<TrashType> clickedType) {
        this.timingError = timingError;
        this.clickedType = clickedType;
    }

    public float getTimingError() {
        return timingError;
    }


    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public Option<TrashType> getClickedType() {
        return clickedType;
    }
}

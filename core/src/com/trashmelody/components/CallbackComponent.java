package com.trashmelody.components;

import com.badlogic.ashley.core.Component;

public class CallbackComponent implements Component {
    public CallbackListener listener;
    public float waitFor;

    public CallbackComponent(CallbackListener callback, float waitFor) {
        this.listener = callback;
        this.waitFor = waitFor;
    }
}

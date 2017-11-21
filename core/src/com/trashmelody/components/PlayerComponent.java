package com.trashmelody.components;

import com.badlogic.ashley.core.Component;


public class PlayerComponent implements Component {
    public int dangerous, recycle, wet, general;

    public PlayerComponent(int dangerous, int recycle, int wet, int general) {
        this.dangerous = dangerous;
        this.recycle = recycle;
        this.wet = wet;
        this.general = general;
    }
}

package com.trashmelody.models.trashes.recycle;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class PlasticBox extends Trash {
    private static final String NAME = "SaiSai";
    private static final String DESC = "A plastic box that wants to find new friends and go explore the world.";

    public PlasticBox(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

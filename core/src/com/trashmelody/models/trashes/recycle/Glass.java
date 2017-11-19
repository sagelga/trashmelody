package com.trashmelody.models.trashes.recycle;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class Glass extends Trash {
    private static final String NAME = "MookMook";
    private static final String DESC = "An empty plastic glass from a bubble milk tea shop. He’s finding a way back to the shop.";

    public Glass(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

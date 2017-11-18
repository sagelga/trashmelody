package com.trashmelody.trashes.recycle;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class Glass extends Trash {
    private static final String NAME = "MookMook";
    private static final String DESC = "An empty plastic glass from a bubble milk tea shop. He’s finding a way back to the shop.";

    public Glass(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

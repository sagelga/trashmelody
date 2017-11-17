package com.trashmelody.trashes.recycle;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class Paper extends Trash {
    private static final String NAME = "The Trio";
    private static final String DESC = "The trio of derpy paper friends that a famous artist has thrown into the bin.";

    public Paper(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

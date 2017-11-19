package com.trashmelody.models.trashes.recycle;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class Note extends Trash {
    private static final String NAME = "Pep";
    private static final String DESC = "The lost piece of Pep Guardiola’s note, so the name \"Pep\" literally comes from his owner.";

    public Note(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

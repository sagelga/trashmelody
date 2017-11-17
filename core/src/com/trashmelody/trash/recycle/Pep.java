package com.trashmelody.trash.recycle;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class Pep extends Trash {
    private static final String NAME = "Pep";
    private static final String DESC = "The lost piece of Pep Guardiola’s note, so the name \"Pep\" literally comes from his owner.";

    public Pep() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.RECYCLE;
    }
}

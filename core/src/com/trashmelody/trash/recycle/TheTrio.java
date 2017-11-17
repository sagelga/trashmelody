package com.trashmelody.trash.recycle;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class TheTrio extends Trash {
    private static final String NAME = "The Trio";
    private static final String DESC = "The trio of derpy paper friends that a famous artist has thrown into the bin.";

    public TheTrio() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.RECYCLE;
    }
}

package com.trashmelody.trash.recycle;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class SaiSai extends Trash {
    private static final String NAME = "SaiSai";
    private static final String DESC = "A plastic box that wants to find new friends and go explore the world.";

    public SaiSai() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.RECYCLE;
    }
}

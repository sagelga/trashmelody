package com.trashmelody.trash.recycle;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class MookMook extends Trash {
    private static final String NAME = "MookMook";
    private static final String DESC = "An empty plastic glass from a bubble milk tea shop. He’s finding a way back to the shop.";

    public MookMook() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.RECYCLE;
    }
}

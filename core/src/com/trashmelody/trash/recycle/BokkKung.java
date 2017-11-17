package com.trashmelody.trash.recycle;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class BokkKung extends Trash {
    private static final String NAME = "BokkKung";
    private static final String DESC = "A cardboard box that used to contain a dog. He hopes to find a new dog and he’d bark \"Box-Box\" like a dog.";

    public BokkKung() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.RECYCLE;
    }
}

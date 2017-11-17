package com.trashmelody.trashes.recycle;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class CardBoard extends Trash {
    private static final String NAME = "BokkKung";
    private static final String DESC = "A cardboard box that used to contain a dog. He hopes to find a new dog and he’d bark \"Box-Box\" like a dog.";

    public CardBoard() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.RECYCLE;
    }
}

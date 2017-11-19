package com.trashmelody.models.trashes.recycle;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class CardBoard extends Trash {
    private static final String NAME = "BokkKung";
    private static final String DESC = "A cardboard box that used to contain a dog. He hopes to find a new dog and he’d bark \"Box-Box\" like a dog.";

    public CardBoard(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

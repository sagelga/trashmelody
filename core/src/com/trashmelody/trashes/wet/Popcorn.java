package com.trashmelody.trashes.wet;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class Popcorn extends Trash {
    private static final String NAME = "Popu-san";
    private static final String DESC = "He is the son of comedy director M-san. Popu-san is a popular actor. He has starred in many popular films such as Trash 1997 and earned Oscar nominations for Get Trash 2008.";

    public Popcorn(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

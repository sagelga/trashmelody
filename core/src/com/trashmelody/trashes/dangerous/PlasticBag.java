package com.trashmelody.trashes.dangerous;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class PlasticBag extends Trash {
    private static final String NAME = "Immortal bag";
    private static final String DESC = "She was born 700 years ago. And as her name says; she is a plastic bag that could live through centuries.";

    public PlasticBag(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

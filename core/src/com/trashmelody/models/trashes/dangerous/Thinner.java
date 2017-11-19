package com.trashmelody.models.trashes.dangerous;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class Thinner extends Trash {
    private static final String NAME = "Thinner the Carpenter";
    private static final String DESC = "A hot and flammable guy. His smell can cause pleasant hallucinations to everyone near him.";

    public Thinner(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

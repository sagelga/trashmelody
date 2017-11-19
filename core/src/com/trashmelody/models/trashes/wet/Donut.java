package com.trashmelody.models.trashes.wet;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class Donut extends Trash {
    private static final String NAME = "Dono-chan";
    private static final String DESC = "Dono-Chan is a teacher of Circle Dance and Sing Academy. Although she is fat; she can dance very well. She and everyone is jealous of her talent.";

    public Donut(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

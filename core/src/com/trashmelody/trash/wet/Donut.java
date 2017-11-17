package com.trashmelody.trash.wet;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class Donut extends Trash {
    private static final String NAME = "Dono-chan";
    private static final String DESC = "Dono-Chan is a teacher of Circle Dance and Sing Academy. Although she is fat; she can dance very well. She and everyone is jealous of her talent.";

    public Donut() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.WET;
    }
}

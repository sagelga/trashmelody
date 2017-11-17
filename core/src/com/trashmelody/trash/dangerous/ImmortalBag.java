package com.trashmelody.trash.dangerous;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class ImmortalBag extends Trash {
    private static final String NAME = "Immortal bag";
    private static final String DESC = "She was born 700 years ago. And as her name says; she is a plastic bag that could live through centuries.";

    public ImmortalBag() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.DANGEROUS;
    }
}

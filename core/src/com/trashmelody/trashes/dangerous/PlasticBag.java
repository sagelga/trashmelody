package com.trashmelody.trashes.dangerous;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class ImmortalBag extends Trash {
    private static final String NAME = "Immortal bag";
    private static final String DESC = "She was born 700 years ago. And as her name says; she is a plastic bag that could live through centuries.";

    public ImmortalBag() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.DANGEROUS;
    }
}

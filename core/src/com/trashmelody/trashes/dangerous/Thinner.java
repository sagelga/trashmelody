package com.trashmelody.trashes.dangerous;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class ThinnerCarpenter extends Trash {
    private static final String NAME = "Thinner the Carpenter";
    private static final String DESC = "A hot and flammable guy. His smell can cause pleasant hallucinations to everyone near him.";

    public ThinnerCarpenter() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.DANGEROUS;
    }
}

package com.trashmelody.trash.dangerous;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class ThinnerCarpenter extends Trash {
    private static final String NAME = "Thinner the Carpenter";
    private static final String DESC = "A hot and flammable guy. His smell can cause pleasant hallucinations to everyone near him.";

    public ThinnerCarpenter() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.DANGEROUS;
    }
}

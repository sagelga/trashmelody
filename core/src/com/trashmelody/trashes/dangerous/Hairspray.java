package com.trashmelody.trashes.dangerous;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class HairsprayChan extends Trash {
    private static final String NAME = "Hairspray-chan";
    private static final String DESC = "Fired from a beauty salon being accused of causing global warming, she then determined to founding her own salon with Wax-kung and Gel-kung to take revenge.";

    public HairsprayChan() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.DANGEROUS;
    }
}

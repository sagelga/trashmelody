package com.trashmelody.trash;

public class OilyOiler extends Trash {
    private static final String NAME = "Oily Oiler";
    private static final String DESC = "Oily oiler is an oil can from the suburb. After he had been emptied petrol, he got thrown away without care.";

    public OilyOiler() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.DANGEROUS;
    }
}

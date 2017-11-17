package com.trashmelody.trash;

public class MrBatteree extends Trash {
    private static final String NAME = "Mr. Batteree";
    private static final String DESC = "Once full of power, then got burned to his full juice. Mr. Batteree is the one that has been powering home appliances for ages.";

    public MrBatteree() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.DANGEROUS;
    }
}

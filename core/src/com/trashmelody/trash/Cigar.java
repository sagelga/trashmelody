package com.trashmelody.trash;

public class Cigar extends Trash {
    private static final String NAME = "Cigar";
    private static final String DESC = "A friend of every man *Cough*. But his health *Cough* is not very well lately *Cough* due to *Cough* his oral cavity, larynx, esophagus, and lung cancer";

    public Cigar() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.DANGEROUS;
    }
}

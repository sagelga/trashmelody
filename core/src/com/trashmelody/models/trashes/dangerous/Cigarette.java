package com.trashmelody.models.trashes.dangerous;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class Cigarette extends Trash {
    private static final String NAME = "Cigar";
    private static final String DESC = "A friend of every man *Cough*. But his health *Cough* is not very well lately *Cough* due to *Cough* his oral cavity, larynx, esophagus, and lung cancer.";

    public Cigarette(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

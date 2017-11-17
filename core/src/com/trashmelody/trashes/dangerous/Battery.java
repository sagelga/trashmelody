package com.trashmelody.trashes.dangerous;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class Battery extends Trash {
    private static final String NAME = "Mr. Batteree";
    private static final String DESC = "Once full of power, then got burned to his full juice. Mr. Batteree is the one that has been powering home appliances for ages.";

    public Battery(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

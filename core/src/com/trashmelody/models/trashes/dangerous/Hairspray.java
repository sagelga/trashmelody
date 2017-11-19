package com.trashmelody.models.trashes.dangerous;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class Hairspray extends Trash {
    private static final String NAME = "Hairspray-chan";
    private static final String DESC = "Fired from a beauty salon being accused of causing global warming, she then determined to founding her own salon with Wax-kung and Gel-kung to take revenge.";

    public Hairspray(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

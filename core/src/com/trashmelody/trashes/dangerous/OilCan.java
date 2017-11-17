package com.trashmelody.trashes.dangerous;

import com.trashmelody.trashes.Trash;
import com.trashmelody.trashes.TrashType;

public class OilCan extends Trash {
    private static final String NAME = "Oily Oiler";
    private static final String DESC = "Oily Oiler is an oil can from the suburb. After he had been emptied petrol, he got thrown away without care.";

    public OilCan(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

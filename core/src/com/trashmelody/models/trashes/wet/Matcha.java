package com.trashmelody.models.trashes.wet;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class Matcha extends Trash {
    private static final String NAME = "Matty";
    private static final String DESC = "His full name is Matcha-sama. He has dark-green eyes, and he has a lot of success with ladies. He has been voted the sexiest man in Trash World many times.";

    public Matcha(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

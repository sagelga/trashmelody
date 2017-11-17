package com.trashmelody.trash.wet;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class Matcha extends Trash {
    private static final String NAME = "Matty";
    private static final String DESC = "His full name is Matcha-sama. He has dark-green eyes, and he has a lot of success with ladies. He has been voted the sexiest man in Trash World many times.";

    public Matcha() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.WET;
    }
}

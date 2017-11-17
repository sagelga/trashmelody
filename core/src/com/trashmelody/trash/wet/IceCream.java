package com.trashmelody.trash.wet;

import com.trashmelody.trash.Trash;
import com.trashmelody.trash.TrashType;

public class IceCream extends Trash {
    private static final String NAME = "Izu-chan";
    private static final String DESC = "Her full name is Izu - Pink Cremu. She is a sweetened frozen girl you'll want to eat if you see one. Her cheek is pink and her hair is white.";

    public IceCream() {
        this.name = NAME;
        this.desc = DESC;
        this.type = TrashType.WET;
    }
}

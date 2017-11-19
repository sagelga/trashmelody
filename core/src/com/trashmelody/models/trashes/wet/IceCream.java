package com.trashmelody.models.trashes.wet;

import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;

public class IceCream extends Trash {
    private static final String NAME = "Izu-chan";
    private static final String DESC = "Her full name is Izu - Pink Cremu. She is a sweetened frozen girl you'll want to eat if you see one. Her cheek is pink and her hair is white.";

    public IceCream(String name, String description, TrashType type) {
        super(name, description, type);
    }
}

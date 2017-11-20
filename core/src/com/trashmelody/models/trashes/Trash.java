package com.trashmelody.models.trashes;

public class Trash {
    protected String name, description;
    protected TrashType type;

    public Trash(String name, String description, TrashType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Trash() {
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public TrashType getType() {
        return this.type;
    }

    public boolean isDangerous() {
        return this.type == TrashType.Dangerous;
    }

    public boolean isWet() {
        return this.type == TrashType.Wet;
    }

    public boolean isRecycle() {
        return this.type == TrashType.Recycle;
    }
}

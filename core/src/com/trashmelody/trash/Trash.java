package com.trashmelody.trash;

public class Trash {
    protected String name, desc;
    protected TrashType type;

    Trash(String name, String desc, TrashType type) {}
    Trash() {}

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public TrashType getType() {
        return this.type;
    }

    public boolean isDangerous() {
        return this.type == TrashType.DANGEROUS;
    }

    public boolean isWet() {
        return this.type == TrashType.WET;
    }

    public boolean isRecycle() {
        return this.type == TrashType.RECYCLE;
    }
}

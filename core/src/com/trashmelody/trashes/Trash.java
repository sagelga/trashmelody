package com.trashmelody.trashes;

public abstract class Trash {
    protected String name, desc;
    protected TrashType type;

    public Trash(String name, String desc, TrashType type) {}
    public Trash() {}

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

package com.trashmelody.trash;

public class Trash {
    protected String name, desc, type;

    Trash(String name, String desc, String type) {}
    Trash() {}

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getType() {
        return this.type;
    }
}

package com.trashmelody.trashes;

import com.trashmelody.trashes.dangerous.OilyOiler;

public class TrashTest {
    public static void main(String[] args) {
        OilyOiler oilyOiler = new OilyOiler();
        System.out.println(oilyOiler.getName());
        System.out.println(oilyOiler.getDesc());
        System.out.println(oilyOiler.getType());
        System.out.println(oilyOiler.isDangerous());
    }
}

package com.trashmelody.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.dangerous.*;
import com.trashmelody.models.trashes.general.*;
import com.trashmelody.models.trashes.recycle.*;
import com.trashmelody.models.trashes.wet.*;
import io.vavr.collection.Array;

@Singleton
public class TrashManager {

    @Inject
    public TrashManager() {
    }

    private Array<Trash> trashes = Array.of(

        // dangerous
        new Trash(Cigarette.NAME, Cigarette.DESCRIPTION, Cigarette.TEXTURE, Cigarette.TYPE, 0),
        new Trash(Hairspray.NAME, Hairspray.DESCRIPTION, Hairspray.TEXTURE, Hairspray.TYPE, 0),
        new Trash(OilCan.NAME, OilCan.DESCRIPTION, OilCan.TEXTURE, OilCan.TYPE, 0),
        new Trash(Thinner.NAME, Thinner.DESCRIPTION, Thinner.TEXTURE, Thinner.TYPE, 0),

        // recycle
        new Trash(CardBoard.NAME, CardBoard.DESCRIPTION, CardBoard.TEXTURE, CardBoard.TYPE, 0),
        new Trash(Glass.NAME, Glass.DESCRIPTION, Glass.TEXTURE, Glass.TYPE, 0),
        new Trash(Note.NAME, Note.DESCRIPTION, Note.TEXTURE, Note.TYPE, 0),
        new Trash(Paper.NAME, Paper.DESCRIPTION, Paper.TEXTURE, Paper.TYPE, 0),
        new Trash(PlasticBox.NAME, PlasticBox.DESCRIPTION, PlasticBox.TEXTURE, PlasticBox.TYPE, 0),

        // wet
        new Trash(Curry.NAME, Curry.DESCRIPTION, Curry.TEXTURE, Curry.TYPE, 0),
        new Trash(Donut.NAME, Donut.DESCRIPTION, Donut.TEXTURE, Donut.TYPE, 0),
        new Trash(IceCream.NAME, IceCream.DESCRIPTION, IceCream.TEXTURE, IceCream.TYPE, 0),
        new Trash(Matcha.NAME, Matcha.DESCRIPTION, Matcha.TEXTURE, Matcha.TYPE, 0),
        new Trash(Popcorn.NAME, Popcorn.DESCRIPTION, Popcorn.TEXTURE, Popcorn.TYPE, 0),

        // general
        new Trash(CeramicPlate.NAME, CeramicPlate.DESCRIPTION, CeramicPlate.TEXTURE, CeramicPlate.TYPE, 0),
        new Trash(Pencil.NAME, Pencil.DESCRIPTION, Pencil.TEXTURE, Pencil.TYPE, 0),
        new Trash(PlasticBag.NAME, PlasticBag.DESCRIPTION, PlasticBag.TEXTURE, PlasticBag.TYPE, 0),
        new Trash(Rag.NAME, Rag.DESCRIPTION, Rag.TEXTURE, Rag.TYPE, 0),
        new Trash(Toothpaste.NAME, Toothpaste.DESCRIPTION, Toothpaste.TEXTURE, Toothpaste.TYPE, 0)

    );

    public Array<Trash> getTrashes() {
        return trashes;
    }

    public Array<Trash> getDiscoveredTrash(int userProgress) {
        return trashes.filter(trash -> userProgress >= trash.getUnlockAt());
    }

}

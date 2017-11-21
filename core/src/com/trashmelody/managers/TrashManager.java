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
        new Trash(Cigarette.NAME, Cigarette.DESCRIPTION, Cigarette.TEXTURE, Assets.CIGARETTE_TRASH, Cigarette.TYPE, 4),
        new Trash(Hairspray.NAME, Hairspray.DESCRIPTION, Hairspray.TEXTURE, Assets.HAIR_SPRAY_TRASH, Hairspray.TYPE, 8),
        new Trash(OilCan.NAME, OilCan.DESCRIPTION, OilCan.TEXTURE, Assets.OIL_CAN_TRASH, OilCan.TYPE, 12),
        new Trash(Thinner.NAME, Thinner.DESCRIPTION, Thinner.TEXTURE, Assets.THINNER_TRASH, Thinner.TYPE, -1),

        // recycle
        new Trash(CardBoard.NAME, CardBoard.DESCRIPTION, CardBoard.TEXTURE, Assets.CARD_BOARD_TRASH, CardBoard.TYPE, -1),
        new Trash(Glass.NAME, Glass.DESCRIPTION, Glass.TEXTURE, Assets.GLASS_TRASH, Glass.TYPE, 9),
        new Trash(Note.NAME, Note.DESCRIPTION, Note.TEXTURE, Assets.NOTE_TRASH, Note.TYPE, 1),
        new Trash(Paper.NAME, Paper.DESCRIPTION, Paper.TEXTURE, Assets.PAPER_TRASH, Paper.TYPE, 13),
        new Trash(PlasticBox.NAME, PlasticBox.DESCRIPTION, PlasticBox.TEXTURE, Assets.PLASTIC_BOX_TRASH, PlasticBox.TYPE, 5),
        new Trash(Bottle.NAME, Bottle.DESCRIPTION, Bottle.TEXTURE, Assets.BOTTLE_TRASH, Bottle.TYPE, 0),

        // wet
        new Trash(Curry.NAME, Curry.DESCRIPTION, Curry.TEXTURE, Assets.CURRY_TRASH, Curry.TYPE, 6),
        new Trash(Donut.NAME, Donut.DESCRIPTION, Donut.TEXTURE, Assets.DONUT_TRASH, Donut.TYPE, -1),
        new Trash(IceCream.NAME, IceCream.DESCRIPTION, IceCream.TEXTURE, Assets.ICE_CREAM_TRASH, IceCream.TYPE, 14),
        new Trash(Matcha.NAME, Matcha.DESCRIPTION, Matcha.TEXTURE, Assets.MATCHA_TRASH, Matcha.TYPE, 10),
        new Trash(Popcorn.NAME, Popcorn.DESCRIPTION, Popcorn.TEXTURE, Assets.POPCORN_TRASH, Popcorn.TYPE, 2),

        // general
        new Trash(CeramicPlate.NAME, CeramicPlate.DESCRIPTION, CeramicPlate.TEXTURE, Assets.PLATE_TRASH, CeramicPlate.TYPE, 3),
        new Trash(Pencil.NAME, Pencil.DESCRIPTION, Pencil.TEXTURE, Assets.PENCIL_TRASH, Pencil.TYPE, -1),
        new Trash(PlasticBag.NAME, PlasticBag.DESCRIPTION, PlasticBag.TEXTURE, Assets.PLASTIC_BAG_TRASH, PlasticBag.TYPE, 15),
        new Trash(Rag.NAME, Rag.DESCRIPTION, Rag.TEXTURE, Assets.RAG_TRASH, Rag.TYPE, 11),
        new Trash(Toothpaste.NAME, Toothpaste.DESCRIPTION, Toothpaste.TEXTURE, Assets.TOOTHPASTE_TRASH, Toothpaste.TYPE, 7)

    );

    public Array<Trash> getTrashes() {
        return trashes;
    }

    public Array<Trash> getDiscoveredTrash(int userProgress) {
        return trashes.filter(trash -> userProgress >= trash.getUnlockAt());
    }

    public Trash getTrashByName(String name) {
        return trashes.filter(trash -> trash.getName().equalsIgnoreCase(name)).head();
    }

}

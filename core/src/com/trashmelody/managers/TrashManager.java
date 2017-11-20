package com.trashmelody.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.trashmelody.models.trashes.Trash;
import com.trashmelody.models.trashes.TrashType;
import com.trashmelody.models.trashes.dangerous.*;
import com.trashmelody.models.trashes.recycle.*;
import com.trashmelody.models.trashes.wet.*;
import io.vavr.collection.Array;

@Singleton
public class TrashManager {

    private Array<Trash> trashes = Array.of(
        new Trash(Cigarette.NAME, Cigarette.DESCRIPTION, Cigarette.TEXTURE, TrashType.Dangerous, 0),
        new Trash(Hairspray.NAME, Hairspray.DESCRIPTION, Hairspray.TEXTURE, TrashType.Dangerous, 0),
        new Trash(OilCan.NAME, OilCan.DESCRIPTION, OilCan.TEXTURE, TrashType.Dangerous, 0),
        new Trash(PlasticBag.NAME, PlasticBag.DESCRIPTION, PlasticBag.TEXTURE, TrashType.Dangerous, 0),
        new Trash(Thinner.NAME, Thinner.DESCRIPTION, Thinner.TEXTURE, TrashType.Dangerous, 0),
        new Trash(CardBoard.NAME, CardBoard.DESCRIPTION, CardBoard.TEXTURE, TrashType.Recycle, 0),
        new Trash(Glass.NAME, Glass.DESCRIPTION, Glass.TEXTURE, TrashType.Recycle, 0),
        new Trash(Note.NAME, Note.DESCRIPTION, Note.TEXTURE, TrashType.Recycle, 0),
        new Trash(Paper.NAME, Paper.DESCRIPTION, Paper.TEXTURE, TrashType.Recycle, 0),
        new Trash(PlasticBox.NAME, PlasticBox.DESCRIPTION, PlasticBox.TEXTURE, TrashType.Recycle, 0),
        new Trash(Curry.NAME, Curry.DESCRIPTION, Curry.TEXTURE, TrashType.Wet, 0),
        new Trash(Donut.NAME, Donut.DESCRIPTION, Donut.TEXTURE, TrashType.Wet, 0),
        new Trash(IceCream.NAME, IceCream.DESCRIPTION, IceCream.TEXTURE, TrashType.Wet, 0),
        new Trash(Matcha.NAME, Matcha.DESCRIPTION, Matcha.TEXTURE, TrashType.Wet, 0),
        new Trash(Popcorn.NAME, Popcorn.DESCRIPTION, Popcorn.TEXTURE, TrashType.Wet, 0)
    );

    @Inject
    public TrashManager() {
    }

    public Array<Trash> getTrashes() {
        return trashes;
    }

    public Array<Trash> getDiscoveredTrash(int userProgress) {
        return trashes.filter(trash -> userProgress >= trash.getUnlockAt());
    }
}

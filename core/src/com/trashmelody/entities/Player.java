package com.trashmelody.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.trashmelody.components.*;

import java.util.Random;

import static com.trashmelody.constants.B2Dvars.PPM;

public class Player extends Entity {

    public Player(PlayerComponent player,
                  TypeComponent type) {
        super.add(player);
        super.add(type);
        super.add(new StateComponent());
    }
}

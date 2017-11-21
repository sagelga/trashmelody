package com.trashmelody.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.google.inject.Inject;
import com.trashmelody.components.Mapper;
import com.trashmelody.components.PhysicsComponent;
import com.trashmelody.components.PlayerComponent;
import com.trashmelody.components.StateComponent;
import com.trashmelody.handlers.KeyboardController;

public class PlayerControlSystem extends IteratingSystem {
    private KeyboardController controller;

    @Inject
    public PlayerControlSystem(KeyboardController controller) {
        super(Family.all(PlayerComponent.class).get(), Systems.getIndex(PlayerControlSystem.class));

        this.controller = controller;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent state = Mapper.state.get(entity);
        PlayerComponent player = Mapper.player.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);

        float yVelocity = physics.body.getLinearVelocity().y;
        float xVelocity = physics.body.getLinearVelocity().x;
        Vector2 worldCenter = physics.body.getWorldCenter();
        // If body going down set to falling status
        if (yVelocity > 0) state.set(StateComponent.STATE_FALLING);

        // If body stationary on y axis
        if (yVelocity == 0) {
            // change to normal status if previous status was falling (no mid air jump)
            if (state.get() == StateComponent.STATE_FALLING) state.set(StateComponent.STATE_NORMAL);

            // set status moving if not falling and moving on x axis
            if (xVelocity != 0) state.set(StateComponent.STATE_MOVING);
        }

        if (controller.keyMap.get(player.dangerous))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, -5F, 0.2F),
                    yVelocity
            );

        if (controller.keyMap.get(player.recycle))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, 5F, 0.2F),
                    yVelocity
            );

        if (!controller.keyMap.get(player.dangerous) && !controller.keyMap.get(player.recycle))
            physics.body.setLinearVelocity(
                    MathUtils.lerp(xVelocity, 0F, 0.15F),
                    yVelocity
            );

        if (controller.keyMap.get(player.wet) && (state.get() == StateComponent.STATE_NORMAL || state.get() == StateComponent.STATE_MOVING)) {
            physics.body.applyLinearImpulse(0, 4F, worldCenter.x, worldCenter.y, true);
            state.set(StateComponent.STATE_JUMPING);
        }
    }
}

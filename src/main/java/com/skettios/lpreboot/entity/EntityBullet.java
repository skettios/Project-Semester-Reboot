package com.skettios.lpreboot.entity;

import com.skettios.lpreboot.entity.component.RigidBody;
import com.skettios.lpreboot.entity.component.SpriteRenderer;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.util.Assets;
import com.skettios.lpreboot.util.Constants;

public class EntityBullet extends Entity
{
    public SpriteRenderer renderer;
    public RigidBody body;

    public EntityBullet()
    {
        super();
        renderer = addComponent(new SpriteRenderer(Assets.getTexture("player_bullet"), RenderEngine.RenderType.GAME_FG));
        body = addComponent(new RigidBody((int) renderer.sprite.getWidth(), (int) renderer.sprite.getHeight()));
        body.info.collisionCategory = Constants.Collisions.CATEGORY_PLAYER_BULLET;
        body.info.collisionMask = Constants.Collisions.MASK_PLAYER_BULLET;
    }

    @Override
    public void onCollide(Entity entity)
    {
        gameState.removeEntity(this);
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        transform.move(0, 10);
    }
}

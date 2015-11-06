package com.skettios.lpreboot.entity;

import com.badlogic.gdx.graphics.Texture;
import com.skettios.lpreboot.entity.component.BehaviorScript;
import com.skettios.lpreboot.entity.component.RigidBody;
import com.skettios.lpreboot.entity.component.RigidBodyDebugRenderer;
import com.skettios.lpreboot.entity.component.SpriteRenderer;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.util.Assets;
import com.skettios.lpreboot.util.Constants;

public class EntityPlayer extends Entity
{
    public SpriteRenderer renderer;
    public BehaviorScript script;
    public RigidBody body;

    public EntityPlayer(Texture texture)
    {
        super();
        renderer = addComponent(new SpriteRenderer(texture, RenderEngine.RenderType.GAME_MG));
        script = addComponent(new BehaviorScript(Assets.getScript("player")));
        body = addComponent(new RigidBody((int) renderer.sprite.getWidth(), (int) renderer.sprite.getHeight()));
        body.info.collisionCategory = Constants.Collisions.CATEGORY_PLAYER_BOUNDS;
        body.info.collisionMask = Constants.Collisions.MASK_PLAYER_BOUNDS;
        addComponent(new RigidBodyDebugRenderer(body));
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        System.out.println(transform.position);
    }
}

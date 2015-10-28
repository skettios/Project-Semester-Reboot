package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.skettios.lpreboot.gfx.RenderEngine.RenderType;
import com.skettios.lpreboot.util.Assets;
import com.skettios.lpreboot.util.Properties;

public class RigidBodyDebugRenderer extends Renderer<RigidBodyDebugRenderer>
{
    private RigidBody body;

    public RigidBodyDebugRenderer(RigidBody body)
    {
        this.body = body;
    }

    @Override
    public RenderType getType()
    {
        return RenderType.GAME_FG;
    }

    @Override
    public void onAdd()
    {
        if (Properties.DEBUG_MODE)
            super.onAdd();
    }

    public void onRemove()
    {
        if (Properties.DEBUG_MODE)
            super.onRemove();
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime)
    {
        batch.draw(new TextureRegion(Assets.getTexture("muertos"), (int) body.bounds.width, (int) body.bounds.height), owner.transform.position.x, owner.transform.position.y);
    }

}

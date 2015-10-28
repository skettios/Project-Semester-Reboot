package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skettios.lpreboot.gfx.RenderEngine;

public class AnimationRenderer extends Renderer<AnimationRenderer>
{
    private RenderEngine.RenderType type;
    private Animation anim;

    public AnimationRenderer(Animation anim, RenderEngine.RenderType type)
    {
        this.type = type;
        this.anim = anim;
    }

    @Override
    public RenderEngine.RenderType getType()
    {
        return type;
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime)
    {
    }

    protected class Animation
    {
        public Animation()
        {
        }
    }
}

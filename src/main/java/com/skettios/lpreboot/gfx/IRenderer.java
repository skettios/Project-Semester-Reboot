package com.skettios.lpreboot.gfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderer
{
    RenderEngine.RenderType getType();
    void render(SpriteBatch batch, float deltaTime);
}

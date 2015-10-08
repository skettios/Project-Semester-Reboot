package com.skettios.lpreboot.gfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class RenderLayer
{
    private List<IRenderer> renderList = new ArrayList<IRenderer>();
    private RenderEngine.RenderType type;

    public RenderLayer(RenderEngine.RenderType type)
    {
        this.type = type;
    }

    public void add(IRenderer renderer)
    {
        if (renderer.getType() == type)
            renderList.add(renderer);
    }

    public void remove(IRenderer renderer)
    {
        if (renderer.getType() == type)
            renderList.remove(renderer);
    }

    public void render(SpriteBatch batch, float deltaTime)
    {
        for (int i = 0; i < renderList.size(); i++)
            renderList.get(i).render(batch, deltaTime);
    }
}

package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.graphics.Color;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.gfx.IRenderer;

public abstract class Renderer extends Component implements IRenderer
{
    public Color color;
    public float alpha;

    public Renderer()
    {
        color = Color.WHITE;
        alpha = 1f;
    }

    @Override
    public void onAdd()
    {
        LPReboot.getInstance().getRenderEngine().add(this);
    }

    @Override
    public void onRemove()
    {
        LPReboot.getInstance().getRenderEngine().remove(this);
    }

    @Override
    public void update(float deltaTime)
    {
    }
}

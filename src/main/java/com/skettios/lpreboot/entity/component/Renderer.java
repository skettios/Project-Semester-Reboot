package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.graphics.Color;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.gfx.IRenderer;

public abstract class Renderer<T extends Renderer<T>> extends Component implements IRenderer
{
    public Color color;
    public float alpha;

    public Renderer()
    {
        color = Color.WHITE;
        alpha = 1f;
    }

    public T setColor(Color color)
    {
        this.color = color;
        return (T) this;
    }

    public T setAlpha(float alpha)
    {
        this.alpha = alpha;
        return (T) this;
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

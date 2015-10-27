package com.skettios.lpreboot.gfx;

import com.skettios.lpreboot.entity.component.SpriteRenderer;

/**
 * Only transition for now is fade until I learn the maths for the rest xd
 */
public class TransitionTween
{
    public enum TransitionType
    {
        FADE_IN,
        FADE_OUT
    }

    private TransitionType type;
    private SpriteRenderer renderer;

    public TransitionTween(TransitionType type, SpriteRenderer renderer)
    {
        this.type = type;
        this.renderer = renderer;
    }

    public boolean doTransition(float step)
    {
        switch (type)
        {
            case FADE_IN:
                return fadeIn(step);
            case FADE_OUT:
                return fadeOut(step);
            default:
                return false;
        }
    }

    private boolean fadeIn(float step)
    {
        float currAlpha = Math.min(1f, renderer.alpha + step);
        renderer.setAlpha(currAlpha);

        return currAlpha == 1f;
    }

    private boolean fadeOut(float step)
    {
        float currAlpha = Math.max(0f, renderer.alpha - step);
        renderer.setAlpha(currAlpha);
        
        return currAlpha == 0f;
    }
}

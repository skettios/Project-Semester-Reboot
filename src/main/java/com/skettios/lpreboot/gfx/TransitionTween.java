package com.skettios.lpreboot.gfx;

import com.skettios.lpreboot.entity.component.Renderer;

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
    private Renderer renderer;

    public TransitionTween(TransitionType type, Renderer renderer)
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
                fadeOut(step);
                break;
            default:
                return false;
        }

        return false;
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

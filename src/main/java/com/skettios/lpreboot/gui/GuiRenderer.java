package com.skettios.lpreboot.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skettios.lpreboot.entity.component.Renderer;
import com.skettios.lpreboot.gfx.RenderEngine;

public class GuiRenderer extends Renderer<GuiRenderer>
{
    private Gui gui;
    public RenderEngine.RenderType type;

    public GuiRenderer(Gui gui, RenderEngine.RenderType type)
    {
        this.gui = gui;
        this.type = type;
    }

    @Override
    public RenderEngine.RenderType getType()
    {
        return type;
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime)
    {
        gui.stage.draw();
    }
}

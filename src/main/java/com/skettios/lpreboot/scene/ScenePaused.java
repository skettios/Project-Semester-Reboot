package com.skettios.lpreboot.scene;

import com.skettios.lpreboot.gui.GuiPaused;

public class ScenePaused extends Scene
{
    public GuiPaused gui = new GuiPaused();

    @Override
    public void initialize()
    {
        addEntity(gui);
    }
}

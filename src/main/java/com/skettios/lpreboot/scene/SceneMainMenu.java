package com.skettios.lpreboot.scene;

import com.skettios.lpreboot.gui.GuiMainMenu;

public class SceneMainMenu extends Scene
{
    public GuiMainMenu gui = new GuiMainMenu();

    @Override
    public void initialize()
    {
        addEntity(gui);
    }
}

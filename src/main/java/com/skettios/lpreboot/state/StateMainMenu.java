package com.skettios.lpreboot.state;

import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.gui.GuiMainMenu;
import com.skettios.lpreboot.util.Assets;

public class StateMainMenu extends State
{
    public static final StateMainMenu INSTANCE = new StateMainMenu();

    public GuiMainMenu gui;

    @Override
    public void onLoad()
    {
        Assets.startLoading();
        Assets.loadTexture("menu_background");
        Assets.endLoading();

        gui = new GuiMainMenu();
        addEntity(gui);
    }

    @Override
    public void onUnload()
    {
        Assets.unloadTexture("menu_background");

        gui.setInputFocus(null);
        LPReboot.getInstance().getStateEngine().pushState(StateGame.INSTANCE);
    }
}

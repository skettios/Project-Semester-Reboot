package com.skettios.lpreboot.scene;

import com.skettios.lpreboot.gui.GuiMainMenu;
import com.skettios.lpreboot.util.Assets;

public class SceneMainMenu extends Scene
{
    public GuiMainMenu gui;

	@Override
	public void loadContent()
	{
		Assets.startLoading();
		Assets.loadTexture("menu_background");
		Assets.endLoading();
	}
    
	@Override
	public void unloadContent()
	{
		Assets.unloadTexture("menu_background");
	}
    
    @Override
    public void initialize()
    {
    	gui = new GuiMainMenu();
    	
        addEntity(gui);
    }
}

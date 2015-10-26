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

	@Override
	public void loadContent()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unloadContent()
	{
		// TODO Auto-generated method stub
		
	}
}

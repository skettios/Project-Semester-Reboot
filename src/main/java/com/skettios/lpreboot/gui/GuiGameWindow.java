package com.skettios.lpreboot.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisTable;
import com.skettios.lpreboot.gfx.RenderEngine.RenderType;
import com.skettios.lpreboot.util.Assets;

public class GuiGameWindow extends Gui
{
	public GuiGameWindow()
	{
		super(RenderType.WINDOW_GUI);
	}

	@Override
	public void initialize()
	{
		VisTable innerTable = new VisTable();
		innerTable.setBackground(new TextureRegionDrawable(new TextureRegion(Assets.getTexture("sidebar"))));
		
		table.align(Align.right);
		table.add(innerTable);
	}

	@Override
	public void onFocus()
	{
		
	}

}

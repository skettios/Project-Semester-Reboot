package com.skettios.lpreboot.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.skettios.lpreboot.gfx.RenderEngine.RenderType;
import com.skettios.lpreboot.util.Assets;

public class GuiGameWindow extends Gui
{
    private VisLabel fps;

    public GuiGameWindow()
    {
        super(RenderType.WINDOW_GUI);
    }

    @Override
    public void initialize()
    {
        VisTable innerTable = new VisTable();
        fps = new VisLabel(String.format("FPS: %d", Gdx.graphics.getFramesPerSecond()));
        innerTable.setBackground(new TextureRegionDrawable(new TextureRegion(Assets.getTexture("sidebar"))));
        innerTable.add(fps);

        table.align(Align.right);
        table.add(innerTable);
    }

    @Override
    public void onFocus()
    {

    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        fps.setText(String.format("FPS: %d", Gdx.graphics.getFramesPerSecond()));
    }
}

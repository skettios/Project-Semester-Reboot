package com.skettios.lpreboot.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.skettios.lpreboot.gfx.RenderEngine;

public class GuiPaused extends Gui
{
    private VisLabel paused;
    private VisLabel resume;
    private VisLabel quit;

    public GuiPaused()
    {
        super(RenderEngine.RenderType.WINDOW_GUI);
    }

    @Override
    public void initialize()
    {
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.absolute("assets/textures/pause_background.png")))));

        paused = new VisLabel();
        resume = new VisLabel();
        quit = new VisLabel();

        VisTable innerTable = new VisTable();

        innerTable.add(paused);
        innerTable.row();
        innerTable.add(resume);
        innerTable.row();
        innerTable.add(quit);

        table.add(innerTable);
    }

    @Override
    public void onFocus()
    {
        paused.setText("Paused");
        resume.setText("Press 'Z' to Resume");
        quit.setText("Press 'ESC' to Quit");
    }
}

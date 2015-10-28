package com.skettios.lpreboot.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.util.Assets;
import com.skettios.lpreboot.util.I18n;

public class GuiGame extends Gui
{
    private VisLabel playerName;

    public GuiGame()
    {
        super(RenderEngine.RenderType.GAME_GUI);
    }

    @Override
    public void initialize()
    {
        VisTable innerTable = new VisTable();
        innerTable.setBackground(new TextureRegionDrawable(new TextureRegion(Assets.getTexture("textbar"))));


        playerName = new VisLabel(I18n.i18n("gui.game.sean_mang"));

        innerTable.add(new VisLabel("Sean Death")).align(Align.left).expandX();
        innerTable.row();
        innerTable.add(playerName).expandY();
        innerTable.align(Align.top);
        innerTable.pad(4, 8, 12, 4);

        table.align(Align.bottom);
        table.add(innerTable);
    }

    @Override
    public void onFocus()
    {
        playerName.setText(I18n.i18n("gui.game.sean_mang"));
    }
}

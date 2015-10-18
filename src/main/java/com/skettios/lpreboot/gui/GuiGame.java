package com.skettios.lpreboot.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.skettios.lpreboot.gfx.RenderEngine;
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
        innerTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.absolute("assets/textures/textbar.png")))));

        playerName = new VisLabel(I18n.i18n("gui.game.player_1"));

        innerTable.add(playerName);
        innerTable.align(Align.topLeft);
        innerTable.padLeft(10);
        innerTable.padTop(10);

        table.align(Align.bottom);
        table.add(innerTable);
    }

    @Override
    public void onFocus()
    {
        playerName.setText(I18n.i18n("gui.game.player_1"));
    }
}

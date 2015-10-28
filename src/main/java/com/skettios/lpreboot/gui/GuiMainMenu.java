package com.skettios.lpreboot.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisList;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.util.Assets;
import com.skettios.lpreboot.util.I18n;

public class GuiMainMenu extends Gui
{
    private VisLabel title;
    private VisList<String> selection;

    private String newGame;
    private String exit;

    public GuiMainMenu()
    {
        super(RenderEngine.RenderType.WINDOW_GUI);
    }

    @Override
    public void initialize()
    {
        table.setBackground(new TextureRegionDrawable(new TextureRegion(Assets.getTexture("menu_background"))));

        table.align(Align.topLeft);

        newGame = I18n.i18n("gui.menu.new_game");
        exit = I18n.i18n("gui.menu.exit");
        title = new VisLabel(I18n.i18n("gui.menu.title"));
        selection = new VisList<String>();

        title.setFontScale(1.05f);
        table.add(title).padTop(50).padLeft(50).expandX().align(Align.left);
        table.row();
        selection.setItems(newGame, exit);

        selection.addListener(new InputListener()
        {
            int currentIndex = 0;

            @Override
            public boolean keyDown(InputEvent event, int keycode)
            {
                if (keycode == Input.Keys.DOWN)
                {
                    if (currentIndex <= selection.getSelection().size() - 1)
                        currentIndex++;
                    else
                        currentIndex = 0;
                }
                if (keycode == Input.Keys.UP)
                {
                    if (currentIndex > currentIndex - 1 && currentIndex != 0)
                        currentIndex--;
                    else
                        currentIndex = selection.getSelection().size();
                }
                if (keycode == Input.Keys.Z)
                {
                    switch (currentIndex)
                    {
                        case 0:
                            LPReboot.getInstance().getStateEngine().popState();
                            break;
                        case 1:
                            Gdx.app.exit();
                            break;
                    }
                }
                selection.setSelectedIndex(currentIndex);

                return true;
            }
        });
        table.add(selection).padLeft(50).padTop(25).expandX().align(Align.left);
    }

    @Override
    public void onFocus()
    {
        stage.setKeyboardFocus(selection);
        title.setText(I18n.i18n("gui.menu.title"));
        newGame = I18n.i18n("gui.menu.new_game");
        exit = I18n.i18n("gui.menu.exit");
    }
}

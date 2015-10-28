package com.skettios.lpreboot.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.gui.GuiPaused;

public class StatePaused extends State
{
    public static final StatePaused INSTANCE = new StatePaused();

    public GuiPaused gui;

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        if (Gdx.input.isKeyJustPressed(Input.Keys.Z))
            LPReboot.getInstance().getStateEngine().popState();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            LPReboot.getInstance().getStateEngine().popState();
            LPReboot.getInstance().getStateEngine().popState();
        }
    }

    @Override
    public void onLoad()
    {
        gui = new GuiPaused();
        addEntity(gui);

        StateGame.INSTANCE.pause();
    }

    @Override
    public void onUnload()
    {
        StateGame.INSTANCE.unpause();
    }
}

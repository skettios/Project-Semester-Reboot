package com.skettios.lpreboot.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.scene.Scene;
import com.skettios.lpreboot.scene.ScenePaused;

public class StatePaused extends State
{
    public StatePaused()
    {
        super(new ScenePaused());
    }

    @Override
    public void onPush()
    {
        States.game.pause();
    }

    @Override
    public void onPop()
    {
        States.game.unpause();
    }

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
}

package com.skettios.lpreboot.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.scene.SceneGame;

public class StateGame extends State
{
    public StateGame()
    {
        super(new SceneGame());
    }

    @Override
    public void onPush()
    {

    }

    @Override
    public void onPop()
    {
        LPReboot.getInstance().getStateEngine().pushState(States.mainMenu);
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            LPReboot.getInstance().getStateEngine().pushState(States.paused);
    }
}

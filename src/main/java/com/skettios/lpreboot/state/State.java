package com.skettios.lpreboot.state;

import com.badlogic.gdx.Input;

public abstract class State
{
    private boolean isPaused = false;

    public State()
    {
    }

    public void pause()
    {
        isPaused = true;
    }

    public void unpause()
    {
        isPaused = false;
    }

    public boolean isPaused()
    {
        return isPaused;
    }

    public abstract void onPush();
    public abstract void onPop();
    public abstract void update(float deltaTime);
}

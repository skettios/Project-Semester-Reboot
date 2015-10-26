package com.skettios.lpreboot.state;


import com.badlogic.gdx.utils.Timer;
import com.skettios.lpreboot.scene.Scene;

public abstract class State
{
    private boolean isPaused = false;
    protected Scene scene;

    public State(Scene scene)
    {
        this.scene = scene;
    }

    public void pause()
    {
        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                isPaused = true;
                cancel();
            }
        }, 0.01f);
    }

    public void unpause()
    {
        Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                isPaused = false;
                cancel();
            }
        }, 0.01f);
    }

    public boolean isPaused()
    {
        return isPaused;
    }
    
    public void clearScene()
    {
    	scene.unloadContent();
        scene.clearEntities();
    }

    public abstract void onPush();
    public abstract void onPop();
    public void update(float deltaTime)
    {
        scene.update(deltaTime);
    }
}

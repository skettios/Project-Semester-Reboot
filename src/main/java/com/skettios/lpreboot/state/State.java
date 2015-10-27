package com.skettios.lpreboot.state;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Timer;
import com.skettios.lpreboot.entity.Entity;

public abstract class State
{
	private List<Entity> entityList = new ArrayList<Entity>();
	
    private boolean isPaused = false;
    
    public void onPush()
    {
    	onLoad();
    }
    
    public void onPop()
    {
    	clearEntities();
    	onUnload();
    }
    
    public abstract void onLoad();
    public abstract void onUnload();
    
    public void addEntity(Entity entity)
    {
        entity.onAdd();
        entity.gameState = this;
        entityList.add(entity);
    }

    public void removeEntity(Entity entity)
    {
        entity.onRemove();
        entityList.remove(entity);
    }

    public boolean containsEntity(Entity entity)
    {
    	return entityList.contains(entity);
    }
    
    private void clearEntities()
    {
        for (int i = 0; i < entityList.size(); i++)
            entityList.get(i).onRemove();

        entityList.clear();
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
    
    public void update(float deltaTime)
    {
        for (int i = 0; i < entityList.size(); i++)
        	entityList.get(i).update(deltaTime);;
    }
}

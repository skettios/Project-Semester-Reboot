package com.skettios.lpreboot.scene;

import com.skettios.lpreboot.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene
{
    private List<Entity> entityList = new ArrayList<Entity>();

    public abstract void loadContent();
    public abstract void unloadContent();
    
    public abstract void initialize();

    public void addEntity(Entity entity)
    {
        entity.onAdd();
        entityList.add(entity);
    }

    public void removeEntity(Entity entity)
    {
        entity.onRemove();
        entityList.remove(entity);
    }

    public void clearEntities()
    {
        for (int i = 0; i < entityList.size(); i++)
            entityList.get(i).onRemove();

        entityList.clear();
    }

    public void update(float deltaTime)
    {
        for (int i = 0; i < entityList.size(); i++)
            entityList.get(i).update(deltaTime);
    }
}

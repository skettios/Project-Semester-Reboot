package com.skettios.lpreboot.state;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.skettios.lpreboot.entity.Entity;
import com.skettios.lpreboot.entity.component.RigidBody;

import java.util.ArrayList;
import java.util.List;

public abstract class State
{
    private List<Entity> entityList = new ArrayList<Entity>();
    private List<CollisionChunk> chunks;

    protected int worldWidth, worldHeight, chunkSize;
    protected boolean processCollisions = false;

    private boolean isPaused = false;

    public State()
    {
        worldWidth = 800;
        worldHeight = 600;
        chunkSize = 200;
    }

    public void onPush()
    {
        if (processCollisions)
        {
            chunks = new ArrayList<CollisionChunk>();

            for (int i = 0; i < worldWidth; i += chunkSize)
                for (int j = 0; j < worldHeight; j += chunkSize)
                    chunks.add(new CollisionChunk(i, j, chunkSize));
        }

        onLoad();
    }

    public void onPop()
    {
        if (chunks != null)
        {
            chunks.clear();
            chunks = null;
            System.gc();
        }

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
            entityList.get(i).update(deltaTime);

        updateCollisions();
    }

    private void updateCollisions()
    {
        if (processCollisions)
        {
            for (int i = 0; i < chunks.size(); i++)
            {
                chunks.get(i).updateEntities(entityList);
                chunks.get(i).doCollision();
            }
        }
    }

    protected class CollisionChunk
    {
        private Rectangle bounds;
        private List<RigidBody> bodies = new ArrayList<RigidBody>();

        private Entity entity;
        private RigidBody body;

        public CollisionChunk(int x, int y, int size)
        {
            bounds = new Rectangle(x, y, size, size);
        }

        public void updateEntities(List<Entity> entities)
        {
            bodies.clear();

            if (entities.size() < 1)
                return;

            for (int i = 0; i < entities.size(); i++)
            {
                entity = entities.get(i);
                if (entity.hasComponent(RigidBody.class))
                {
                    body = entities.get(i).getComponent(RigidBody.class);
                    if (body.bounds.overlaps(bounds))
                        bodies.add(body);
                }
            }
        }

        public void doCollision()
        {
            if (bodies.size() < 2)
                return;

            for (int i = 0; i < bodies.size(); i++)
            {
                for (int j = 0; j < bodies.size(); j++)
                {
                    if (i != j)
                    {
                        if (bodies.get(i).canCollide(bodies.get(j)))
                            if (bodies.get(i).bounds.overlaps(bodies.get(j).bounds))
                                bodies.get(i).owner.onCollide(bodies.get(j).owner);
                    }
                }
            }
        }
    }
}

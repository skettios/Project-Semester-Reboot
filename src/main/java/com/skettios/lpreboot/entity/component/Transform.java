package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.math.Vector2;
import com.skettios.lpreboot.entity.Entity;

public class Transform extends Component
{
    public Vector2 position;
    public float rotation;

    public Transform()
    {
        position = new Vector2(0, 0);
        rotation = 0f;
    }

    public void move(float x, float y)
    {
        position.add(x, y);
    }

    public Entity setPosition(float x, float y)
    {
        position = new Vector2(x, y);
        return owner;
    }

    public Entity setPosition(Vector2 position)
    {
        this.position = position;
        return owner;
    }

    @Override
    public void onAdd()
    {
    }

    @Override
    public void onRemove()
    {
    }

    @Override
    public void update(float deltaTime)
    {
    }
}

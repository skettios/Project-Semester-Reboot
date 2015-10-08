package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.math.Vector2;

public class Transform extends Component
{
    public Vector2 position;
    public float rotation;

    public Transform()
    {
        position = Vector2.Zero;
        rotation = 0f;
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

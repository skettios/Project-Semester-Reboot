package com.skettios.lpreboot.entity.component;

import com.skettios.lpreboot.entity.Entity;

public abstract class Component
{
    public Entity owner;

    public abstract void onAdd();

    public abstract void onRemove();

    public abstract void update(float deltaTime);
}

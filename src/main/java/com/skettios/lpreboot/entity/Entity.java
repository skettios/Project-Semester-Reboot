package com.skettios.lpreboot.entity;

import com.skettios.lpreboot.entity.component.Component;
import com.skettios.lpreboot.entity.component.Transform;

import java.util.HashMap;
import java.util.Map;

public class Entity
{
    private Map<Class<? extends Component>, Component> components = new HashMap<Class<? extends Component>, Component>();

    public Transform transform;

    public Entity()
    {
        transform = addComponent(new Transform());
    }

    public <T extends Component> T addComponent(T component)
    {
        component.owner = this;
        components.put(component.getClass(), component);

        return (T) component;
    }

    public <T extends Component> void removeComponent(Class<T> clazz)
    {
        components.remove(clazz).onRemove();
    }

    @SuppressWarnings("unchecked")
	public <T extends Component> T getComponent(Class<T> clazz)
    {
        return (T) components.get(clazz);
    }

    public void onAdd()
    {
        for (Component component : components.values())
            component.onAdd();
    }

    public void onRemove()
    {
        for (Component component : components.values())
            component.onRemove();
    }

    public void update(float deltaTime)
    {
        for (Component component : components.values())
            component.update(deltaTime);
    }
}

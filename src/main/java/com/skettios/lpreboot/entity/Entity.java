package com.skettios.lpreboot.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skettios.lpreboot.entity.component.Component;
import com.skettios.lpreboot.entity.component.Transform;
import com.skettios.lpreboot.state.State;

//TODO(skettios): Figure out better way to call onCollide and better place for it...
public class Entity
{
    private Map<Class<? extends Component>, Component> components = new HashMap<Class<? extends Component>, Component>();

    public State gameState;
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

    public boolean hasComponent(Class<? extends Component> clazz)
    {
    	if (components.containsKey(clazz))
    		return true;
    	
    	return false;
    }
    
    public void onCollide(Entity entity)
    {
    }
    
    public void onAdd()
    {
    	List<Component> componentList = new ArrayList<Component>(components.values());
        for (int i = 0; i < componentList.size(); i++)
        	componentList.get(i).onAdd();
    }

    public void onRemove()
    {
    	List<Component> componentList = new ArrayList<Component>(components.values());
        for (int i = 0; i < componentList.size(); i++)
        	componentList.get(i).onRemove();
    }

    public void update(float deltaTime)
    {
        for (Component component : components.values())
            component.update(deltaTime);
    }
}

package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.math.Rectangle;

public class RigidBody extends Component
{
	private int width, height;
	public Rectangle bounds;
	
	public RigidBody(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void onAdd()
	{
		bounds = new Rectangle(owner.transform.position.x, owner.transform.position.y, width, height);
	}

	@Override
	public void onRemove()
	{
	}

	@Override
	public void update(float deltaTime)
	{
		bounds.x = owner.transform.position.x;
		bounds.y = owner.transform.position.y;
	}
}

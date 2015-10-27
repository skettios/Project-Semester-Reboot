package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.skettios.lpreboot.gfx.RenderEngine.RenderType;
import com.skettios.lpreboot.util.Assets;

public class RigidBodyDebugRenderer extends Renderer<RigidBodyDebugRenderer>
{
	private RigidBody body;
	
	public RigidBodyDebugRenderer(RigidBody body)
	{
		this.body = body;
	}
	
	@Override
	public RenderType getType()
	{
		return RenderType.GAME_FG;
	}

	@Override
	public void render(SpriteBatch batch, float deltaTime)
	{
		batch.draw(new TextureRegion(Assets.getTexture("muertos"), (int) body.bounds.width, (int) body.bounds.height), body.bounds.x, body.bounds.y);
	}
	
}

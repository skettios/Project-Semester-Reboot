package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skettios.lpreboot.gfx.RenderEngine;

public class SpriteRenderer extends Renderer
{
    private Sprite sprite;
    private RenderEngine.RenderType type;

    public SpriteRenderer(Sprite sprite, RenderEngine.RenderType type)
    {
        this.sprite = sprite;
        this.type = type;

        sprite.setPosition(owner.transform.position.x, owner.transform.position.y);
        sprite.setRotation(owner.transform.rotation);
    }

    public SpriteRenderer(Texture texture, RenderEngine.RenderType type)
    {
        this(new Sprite(texture), type);
    }

    @Override
    public RenderEngine.RenderType getType()
    {
        return type;
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime)
    {
        if (sprite.getColor() != color)
            sprite.setColor(color);

        if (sprite.getColor().a != alpha)
            sprite.setAlpha(alpha);

        if (sprite.getX() != owner.transform.position.x || sprite.getY() != owner.transform.position.y)
            sprite.setPosition(owner.transform.position.x, owner.transform.position.y);

        if (sprite.getRotation() != owner.transform.rotation)
            sprite.setRotation(owner.transform.rotation);

        sprite.draw(batch);
    }
}

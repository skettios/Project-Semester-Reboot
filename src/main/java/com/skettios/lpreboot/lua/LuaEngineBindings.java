package com.skettios.lpreboot.lua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.skettios.lpreboot.entity.EntityBullet;
import com.skettios.lpreboot.entity.component.SpriteRenderer;
import com.skettios.lpreboot.util.I18n;

import javax.script.SimpleBindings;
import java.util.Random;

public class LuaEngineBindings extends SimpleBindings
{
    public LuaEngineBindings()
    {
        super();
        // Java Classes
        put("Math", Math.class);
        put("Random", Random.class);
        put("System", System.class);

        // Gdx Classes
        put("Color", Color.class);
        put("Gdx", Gdx.class);
        put("Input", Input.class);
        put("Keys", Input.Keys.class);
        put("Sprite", Sprite.class);
        put("Texture", Texture.class);

        // LPR Classes
        put("EntityBullet", EntityBullet.class);
        put("Timer", Timer.class);
        put("Task", Timer.Task.class);
        put("I18n", I18n.class);
        put("SpriteRenderer", SpriteRenderer.class);
    }
}

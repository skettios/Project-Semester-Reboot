package com.skettios.lpreboot.lua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
    }
}

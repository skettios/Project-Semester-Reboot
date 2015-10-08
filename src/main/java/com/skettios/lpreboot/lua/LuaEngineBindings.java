package com.skettios.lpreboot.lua;

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
        put("Sprite", Sprite.class);
        put("Texture", Texture.class);
    }
}

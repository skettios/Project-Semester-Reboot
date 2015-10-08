package com.skettios.lpreboot.entity.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.skettios.lpreboot.lua.LuaEngine;

public class Script extends Component
{
    private LuaEngine luaEngine;

    public Script(FileHandle file)
    {
        luaEngine = new LuaEngine();
        luaEngine.evaluateScript(file);
        luaEngine.callFunction("initialize", owner);
    }

    public Script(String file)
    {
        this(Gdx.files.absolute(file));
    }

    @Override
    public void onAdd()
    {
        luaEngine.callFunction("onAdd", owner);
    }

    @Override
    public void onRemove()
    {
        luaEngine.callFunction("onRemove", owner);
    }

    @Override
    public void update(float deltaTime)
    {
        luaEngine.callFunction("update", owner, deltaTime);
    }
}

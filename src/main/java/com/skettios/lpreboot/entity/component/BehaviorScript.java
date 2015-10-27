package com.skettios.lpreboot.entity.component;

import com.skettios.lpreboot.lua.LuaEngine;
import com.skettios.lpreboot.util.Assets;
import com.skettios.lpreboot.util.ScriptLoader.Script;

public class BehaviorScript extends Component
{
    private LuaEngine luaEngine;

    public BehaviorScript(Script script)
    {
        luaEngine = new LuaEngine();
        luaEngine.evaluateScript(script);
        luaEngine.callFunction("initialize", owner);
    }

    public BehaviorScript(String alias)
    {
        this(Assets.getScript(alias));
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

package com.skettios.lpreboot.lua;

import com.naef.jnlua.script.LuaScriptEngineFactory;
import com.skettios.lpreboot.util.ScriptLoader.Script;

import javax.script.*;

public class LuaEngine
{
    private LuaScriptEngineFactory factory = new LuaScriptEngineFactory();
    private ScriptEngine engine = factory.getScriptEngine();

    public LuaEngine()
    {
        engine.setBindings(new LuaEngineBindings(), ScriptContext.ENGINE_SCOPE);
    }

    public Object evaluateScript(Script script)
    {
        try
        {
            return ((Compilable) engine).compile(script.data).eval();
        }
        catch (ScriptException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public Object callFunction(String funcName, Object... args)
    {
        try
        {
            return ((Invocable) engine).invokeFunction(funcName, args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}

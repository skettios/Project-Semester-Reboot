package com.skettios.lpreboot.lua;

import javax.script.Compilable;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.badlogic.gdx.files.FileHandle;
import com.naef.jnlua.script.LuaScriptEngineFactory;

public final class LuaEngine
{
    private LuaScriptEngineFactory factory = new LuaScriptEngineFactory();
    private ScriptEngine engine = factory.getScriptEngine();

    public LuaEngine()
    {
        engine.setBindings(new LuaEngineBindings(), ScriptContext.ENGINE_SCOPE);
    }

    public Object evaluateScript(FileHandle handle)
    {
        try
        {
            return ((Compilable) engine).compile(handle.reader()).eval();
        }
        catch (ScriptException e)
        {
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
        }

        return null;
    }
}

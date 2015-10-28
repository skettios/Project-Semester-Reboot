package com.skettios.lpreboot.util;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.skettios.lpreboot.util.ScriptLoader.Script;
import com.skettios.lpreboot.util.ScriptLoader.ScriptLoaderParamters;

public class ScriptLoader extends AsynchronousAssetLoader<Script, ScriptLoaderParamters>
{
    private ScriptLoaderInfo info = new ScriptLoaderInfo();

    public ScriptLoader(FileHandleResolver resolver)
    {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, ScriptLoaderParamters parameter)
    {
        info.fileName = fileName;
        info.data = file.readString();
    }

    @Override
    public Script loadSync(AssetManager manager, String fileName, FileHandle file, ScriptLoaderParamters parameter)
    {
        if (info == null)
            return null;

        Script ret = new Script();
        ret.fileName = info.fileName;
        ret.data = info.data;

        return ret;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, ScriptLoaderParamters parameter)
    {
        return null;
    }

    protected class ScriptLoaderParamters extends AssetLoaderParameters<Script>
    {
    }

    protected class ScriptLoaderInfo
    {
        public String fileName, data;
    }

    public class Script
    {
        public String fileName, data;
    }
}

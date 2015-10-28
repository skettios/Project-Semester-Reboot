package com.skettios.lpreboot.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonValue.JsonIterator;
import com.skettios.lpreboot.util.AnimationLoader.Animation;
import com.skettios.lpreboot.util.ScriptLoader.Script;

public final class Assets
{
    private static String manifest;
    private static AssetManager assetManager;

    private static boolean canLoad = false;

    public enum AssetType
    {
        ANIMATIONS("assets/animations", Animation.class),
        MUSIC("assets/audio/music", Music.class),
        SOUNDS("assets/audio/sounds", Sound.class),
        TEXTURES("assets/textures", Texture.class),
        SCRIPTS("assets/scripts", Script.class);

        public String dir;
        public Class<?> clazz;

        private AssetType(String dir, Class<?> clazz)
        {
            this.dir = dir;
            this.clazz = clazz;
        }
    }

    static
    {
        AbsoluteFileHandleResolver resolver = new AbsoluteFileHandleResolver();
        manifest = Gdx.files.absolute("assets/manifest.json").readString();
        assetManager = new AssetManager(resolver);
        assetManager.setLoader(Script.class, new ScriptLoader(resolver));
    }

    public static void startLoading()
    {
        canLoad = true;
    }

    public static void endLoading()
    {
        assetManager.finishLoading();
        canLoad = false;
    }

    public static boolean load(AssetType type, String alias)
    {
        if (!canLoad)
            return false;

        JsonValue root = getAssetsForType(type);
        JsonIterator rootIter = root.iterator();
        JsonValue value;
        while (rootIter.hasNext())
        {
            value = rootIter.next();
            if (value.name().equals(alias))
            {
                if (!assetManager.isLoaded(type.dir + "/" + value.asString()))
                {
                    assetManager.load(type.dir + "/" + value.asString(), type.clazz);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }

        return false;
    }

    public static Object get(AssetType type, String alias)
    {
        if (canLoad)
            return null;

        JsonValue root = getAssetsForType(type);
        JsonIterator rootIter = root.iterator();
        JsonValue value;
        while (rootIter.hasNext())
        {
            value = rootIter.next();
            if (value.name().equals(alias))
                if (assetManager.isLoaded(type.dir + "/" + value.asString()))
                    return assetManager.get(type.dir + "/" + value.asString());
        }

        return null;
    }

    public static boolean unload(AssetType type, String alias)
    {
        JsonValue root = getAssetsForType(type);
        JsonIterator rootIter = root.iterator();
        JsonValue value;
        while (rootIter.hasNext())
        {
            value = rootIter.next();
            if (value.name().equals(alias))
            {
                if (assetManager.isLoaded(type.dir + "/" + value.asString()))
                {
                    assetManager.unload(type.dir + "/" + value.asString());
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }

        return false;
    }

    public static Music getMusic(String alias)
    {
        return (Music) get(AssetType.MUSIC, alias);
    }

    public static Script getScript(String alias)
    {
        return (Script) get(AssetType.SCRIPTS, alias);
    }

    public static Sound getSound(String alias)
    {
        return (Sound) get(AssetType.SOUNDS, alias);
    }

    public static Texture getTexture(String alias)
    {
        return (Texture) get(AssetType.TEXTURES, alias);
    }

    public static boolean loadMusic(String alias)
    {
        return load(AssetType.MUSIC, alias);
    }

    public static boolean loadScript(String alias)
    {
        return load(AssetType.SCRIPTS, alias);
    }

    public static boolean loadSound(String alias)
    {
        return load(AssetType.SOUNDS, alias);
    }

    public static boolean loadTexture(String alias)
    {
        return load(AssetType.TEXTURES, alias);
    }

    public static boolean unloadMusic(String alias)
    {
        return unload(AssetType.MUSIC, alias);
    }

    public static boolean unloadScript(String alias)
    {
        return unload(AssetType.SCRIPTS, alias);
    }

    public static boolean unloadSound(String alias)
    {
        return unload(AssetType.SOUNDS, alias);
    }

    public static boolean unloadTexture(String alias)
    {
        return unload(AssetType.SCRIPTS, alias);
    }

    private static JsonValue getAssetsForType(AssetType type)
    {
        JsonValue root = new JsonReader().parse(manifest);
        JsonIterator rootIterator = root.iterator();
        JsonValue currentChild;
        while (rootIterator.hasNext())
        {
            currentChild = rootIterator.next();
            if (currentChild.name().equalsIgnoreCase(type.name()))
                return currentChild;
        }

        return null;
    }

    public static void clearAssets()
    {
        assetManager.clear();
    }

    public static void printDiagnostics()
    {
        System.out.println(assetManager.getDiagnostics());
    }
}

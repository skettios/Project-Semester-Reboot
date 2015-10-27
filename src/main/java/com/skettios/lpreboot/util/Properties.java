package com.skettios.lpreboot.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;

public class Properties
{
    private static java.util.Properties properties = new java.util.Properties();
    private static FileHandle propertiesFile = Gdx.files.absolute("LPReboot.cfg");

    public static boolean DEBUG_MODE;
    public static String LANGUAGE;

    static
    {
        try
        {
            if (!propertiesFile.exists())
            {
                generateDefaults();
                properties.store(propertiesFile.writer(false), "LPReboot Properties");
            }
        }
        catch (IOException e)
        {
        }
    }

    public static void load()
    {
        try
        {
            generateDefaults();
            properties.load(propertiesFile.reader());

            DEBUG_MODE = Boolean.parseBoolean(properties.getProperty("DebugMode"));
            LANGUAGE = properties.getProperty("Lang");

            properties.store(propertiesFile.writer(false), "LPReboot Properties");
        }
        catch (IOException e)
        {
        }
    }

    public static void set(String key, String value)
    {
        try
        {
            properties.setProperty(key, value);
            properties.store(propertiesFile.writer(false), "LPReboot Properties");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void generateDefaults()
    {
        properties.setProperty("DebugMode", Boolean.toString(false));
        properties.setProperty("Lang", "es_MX");
    }
}

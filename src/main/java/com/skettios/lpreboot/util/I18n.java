package com.skettios.lpreboot.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.Map;

public class I18n
{
    private static final FileHandle langDir = Gdx.files.absolute("assets/lang");
    private static I18nContainer translation;

    public static void load()
    {
        translation = getTranslation(Properties.LANGUAGE);
    }

    public static void reload(String langCode)
    {
        System.out.println("RELOAD");
        translation = getTranslation(langCode);
    }

    public static String i18n(String key)
    {
        return translation.translations.get(key);
    }

    private static I18nContainer getTranslation(String langCode)
    {
        FileHandle[] langFiles = langDir.list(".json");
        for (int i = 0; i < langFiles.length; i++)
        {
            if (langFiles[i].nameWithoutExtension().equalsIgnoreCase(langCode))
                return new I18nContainer(langCode, langFiles[i]);
        }

        return null;
    }

    protected static class I18nContainer
    {
        private String langCode;
        private Map<String, String> translations;

        public I18nContainer(String langCode, FileHandle langFile)
        {
            this.langCode = langCode;
            translations = parseLanguageFile(langFile);
        }

        private Map<String, String> parseLanguageFile(FileHandle langFile)
        {
            Map<String, String> ret = new HashMap<String, String>();
            JsonValue langFileParsed = new JsonReader().parse(langFile);
            JsonValue.JsonIterator langFileIterator = langFileParsed.iterator();
            String key = "";
            while (langFileIterator.hasNext())
            {
                JsonValue translation = langFileIterator.next();
                if (translation.isObject())
                {
                    key = translation.name();
                    parseObject(translation, ret, key);
                }
                else if (translation.isString())
                {
                    key = translation.name();
                    ret.put(key, translation.asString());
                }
                else
                {
                    continue;
                }
            }

            return ret;
        }

        private void parseObject(JsonValue value, Map<String, String> map, String key)
        {
            String defaultKey = key;

            if (value.isObject())
            {
                JsonValue.JsonIterator iterator = value.iterator();
                while (iterator.hasNext())
                {
                    key = defaultKey;

                    JsonValue innerValue = iterator.next();
                    if (innerValue.isObject())
                    {
                        key += "." + innerValue.name();
                        parseObject(innerValue, map, key);
                    }
                    else
                    {
                        key += "." + innerValue.name();
                        map.put(key, innerValue.asString());
                    }
                }
            }
        }
    }
}

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
    private static Map<String, I18nContainer> translations;

    public static void load()
    {
        translations = getTranslations(getLanguageFiles(langDir));
    }

    private static FileHandle[] getLanguageFiles(FileHandle dir)
    {
        return dir.list(".json");
    }

    public static String i18n(String key)
    {
        return translations.get(Properties.LANGUAGE).translations.get(key);
    }

    private static Map<String, I18nContainer> getTranslations(FileHandle[] langFiles)
    {
        Map<String, I18nContainer> ret = new HashMap<String, I18nContainer>();
        String langCode = "";
        for (int i = 0; i < langFiles.length; i++)
        {
            langCode = langFiles[i].nameWithoutExtension();
            ret.put(langCode, new I18nContainer(langCode, langFiles[i]));
        }
        return ret;
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
            String category = "";
            String key = "";
            String value = "";
            while (langFileIterator.hasNext())
            {
                JsonValue translation = langFileIterator.next();
                if (translation.isObject())
                {
                    category = translation.name();
                    JsonValue.JsonIterator innerIterator = translation.iterator();
                    if (innerIterator == null)
                    {
                        System.out.println("innerIterator is null!");
                        continue;
                    }

                    while (innerIterator.hasNext())
                    {
                        JsonValue innerValue = innerIterator.next();
                        key = category + "." + innerValue.name();
                        value = innerValue.asString();

                        ret.put(key, value);
                    }
                }
                else if (translation.isString())
                {
                    key = translation.name();
                    value = translation.asString();

                    ret.put(key, value);
                }
                else
                {
                    continue;
                }
            }
            return ret;
        }
    }
}

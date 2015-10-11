package com.skettios.lpreboot;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.kotcrab.vis.ui.VisUI;
import com.naef.jnlua.NativeSupport;
import com.naef.jnlua.NativeSupport.Loader;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.state.StateEngine;
import com.skettios.lpreboot.state.States;
import com.skettios.lpreboot.util.I18n;
import com.skettios.lpreboot.util.Properties;

import java.io.File;

public class LPReboot extends ApplicationAdapter
{
    private static LPReboot instance;

    private RenderEngine renderEngine;
    private StateEngine stateEngine;

    public static void main(String[] args)
    {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        config.resizable = false;
        config.addIcon("icon.png", FileType.Internal);

        NativeLoader.load();

        new LwjglApplication(new LPReboot(), config);
    }

    @Override
    public void create()
    {
        configureGame();

        instance = this;

        renderEngine = new RenderEngine();
        stateEngine = new StateEngine();

        stateEngine.pushState(States.MAIN_MENU);
    }

    @Override
    public void render()
    {
        // Update
        handleGlobalKeyCombinations();
        stateEngine.update(1f / 60f);

        // Render
        renderEngine.render(1f / 60f);
    }

    public static LPReboot getInstance()
    {
        return instance;
    }

    public RenderEngine getRenderEngine()
    {
        return renderEngine;
    }

    public StateEngine getStateEngine()
    {
        return stateEngine;
    }

    private void configureGame()
    {
        Properties.load();

        I18n.load();

        System.out.println(I18n.i18n("test"));
        System.out.println(I18n.i18n("gui.test"));

        VisUI.load();
        Gdx.input.setCursorCatched(true);
    }

    private void handleGlobalKeyCombinations()
    {
        if ((Gdx.input.isKeyPressed(Keys.ALT_LEFT) || Gdx.input.isKeyPressed(Keys.ALT_RIGHT)) && Gdx.input.isKeyPressed(Keys.ENTER))
        {
            if (!Gdx.graphics.isFullscreen())
            {
                Gdx.graphics.setDisplayMode(800, 600, true);
            }
            else
            {
                Gdx.graphics.setDisplayMode(800, 600, false);
            }
        }
    }

    protected static class NativeLoader
    {
        public static void load()
        {
            SharedLibraryLoader loader = new SharedLibraryLoader();
            File nativesDir = null;
            try
            {
                if (SharedLibraryLoader.isWindows)
                {
                    if (SharedLibraryLoader.is64Bit)
                    {
                        nativesDir = loader.extractFile("windows/64-bit/lua52.dll", null).getParentFile();
                        loader.extractFile("windows/64-bit/jnlua52.dll", nativesDir.getName());
                        loader.extractFile("windows/64-bit/javavm.dll", nativesDir.getName());
                    }
                    else
                    {
                        // TODO(skettios): GET 32-BIT LUA WINDOWS BINARIES
                    }
                }

                if (SharedLibraryLoader.isLinux)
                {
                    if (SharedLibraryLoader.is64Bit)
                    {
                        // TODO(skettios): GET 64-BIT LUA LINUX BINARIES
                    }
                    else
                    {
                        // TODO(skettios): GET 32-BIT LUA LINUX BINARIES
                    }
                }

                if (SharedLibraryLoader.isMac)
                {
                    if (SharedLibraryLoader.is64Bit)
                    {
                        // TODO(skettios): GET 64-BIT LUA MAC BINARIES
                    }
                    else
                    {
                        // TODO(skettios): GET 32-BIT LUA MAC BINARIES
                    }
                }
            }
            catch (Throwable ex)
            {
                throw new GdxRuntimeException("Unable to extract JNLua natives.", ex);
            }

            final File finalNativesDir = nativesDir;
            NativeSupport.getInstance().setLoader(new Loader()
            {
                @Override
                public void load()
                {
                    System.load(finalNativesDir.getAbsolutePath() + "/lua52.dll");
                    System.load(finalNativesDir.getAbsolutePath() + "/jnlua52.dll");
                    System.load(finalNativesDir.getAbsolutePath() + "/javavm.dll");
                }
            });
        }
    }
}

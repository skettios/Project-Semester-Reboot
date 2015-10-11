package com.skettios.lpreboot.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.entity.Entity;
import com.skettios.lpreboot.entity.component.Script;
import com.skettios.lpreboot.entity.component.SpriteRenderer;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.gfx.TransitionTween;
import com.skettios.lpreboot.gui.Gui;
import com.skettios.lpreboot.scene.Scene;
import com.skettios.lpreboot.scene.SceneGame;
import com.skettios.lpreboot.scene.SceneMainMenu;
import com.skettios.lpreboot.util.I18n;

public class States
{
    public static State MAIN_MENU = new State()
    {
        private Scene scene = new SceneMainMenu();
        private Gui gui = new Gui(RenderEngine.RenderType.WINDOW_GUI)
        {
            @Override
            public void initialize()
            {
                VisLabel title = new VisLabel("Project Semester Reboot");
                VisLabel cont = new VisLabel("Press Z to continue.");
                VisLabel exit = new VisLabel("Press Escape to exit.");
                table.align(Align.center);
                table.add(title);
                table.row();
                table.add(cont);
                table.row();
                table.add(exit);
            }

            @Override
            public void onFocus()
            {

            }
        };

        @Override
        public void onPush()
        {
            scene.addEntity(gui);
        }

        @Override
        public void onPop()
        {
            LPReboot.getInstance().getStateEngine().pushState(States.GAME);
            scene.clearEntities();
        }

        @Override
        public void update(float deltaTime)
        {
            if (Gdx.input.isKeyJustPressed(Input.Keys.Z))
                LPReboot.getInstance().getStateEngine().popState();

            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
                Gdx.app.exit();

            scene.update(deltaTime);
        }
    };

    public static State GAME = new State()
    {
        private Scene scene = new SceneGame();
        private Gui gui = new Gui(RenderEngine.RenderType.GAME_GUI)
        {
            @Override
            public void initialize()
            {
                Table innerTable = new Table(VisUI.getSkin());
                innerTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.absolute("assets/textures/textbar.png")))));

                innerTable.add(new VisLabel(I18n.i18n("gui.player_1")));
                innerTable.align(Align.topLeft);
                innerTable.padLeft(10);
                innerTable.padTop(10);

                table.align(Align.bottom);
                table.add(innerTable);
            }

            @Override
            public void onFocus()
            {

            }
        };

        private Entity player = new Entity()
        {
            @Override
            public String toString()
            {
                return "PLAYER";
            }
        };
        private Entity gameBG = new Entity();
        private Entity windowBG = new Entity();

        @Override
        public void onPush()
        {
            player.addComponent(new SpriteRenderer(new Texture(Gdx.files.absolute("assets/textures/player.png")), RenderEngine.RenderType.GAME_FG));
            player.addComponent(new Script(Gdx.files.absolute("assets/scripts/player_logic.lua")));
            windowBG.addComponent(new SpriteRenderer(new Texture(Gdx.files.absolute("assets/textures/background.png")), RenderEngine.RenderType.WINDOW_BG));
            gameBG.addComponent(new SpriteRenderer(new Texture(Gdx.files.absolute("assets/textures/game_background.png")), RenderEngine.RenderType.GAME_BG).setColor(Color.DARK_GRAY).setAlpha(0.75f));

            scene.addEntity(gui);
            scene.addEntity(player.transform.setPosition(175, 75));
            scene.addEntity(windowBG);
            scene.addEntity(gameBG);
        }

        @Override
        public void onPop()
        {
            LPReboot.getInstance().getStateEngine().pushState(States.MAIN_MENU);
            scene.clearEntities();
        }

        @Override
        public void update(float deltaTime)
        {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
                LPReboot.getInstance().getStateEngine().pushState(States.PAUSED);

            scene.update(deltaTime);

            System.out.println("BG" + gameBG.transform.position);
        }
    };

    public static State PAUSED = new State()
    {
        private Scene scene = new SceneGame();

        private Gui gui = new Gui(RenderEngine.RenderType.WINDOW_GUI)
        {
            @Override
            public void initialize()
            {
                addComponent(new SpriteRenderer(new Texture(Gdx.files.absolute("assets/textures/pause_background.png")), RenderEngine.RenderType.WINDOW_FG).setAlpha(0));

                VisLabel paused = new VisLabel("Paused");
                VisLabel resume = new VisLabel("Press Escape to Resume");
                VisLabel quit = new VisLabel("Press Q to Exit");

                paused.setColor(Color.GREEN);
                resume.setColor(Color.GREEN);
                quit.setColor(Color.GREEN);

                table.add(paused);
                table.row();
                table.add(resume);
                table.row();
                table.add(quit);
            }

            @Override
            public void onFocus()
            {
                table.setPosition(0, -500);
                gui.getComponent(SpriteRenderer.class).setAlpha(0);
            }

            @Override
            public void update(float deltaTime)
            {
                super.update(deltaTime);
                System.out.println(table.getY());
                float newPos = Math.min(0, table.getY() + 5);
                table.setPosition(0, newPos);
            }
        };
        private TransitionTween fadeIn;

        @Override
        public void onPush()
        {
            scene.addEntity(gui);
            fadeIn = new TransitionTween(TransitionTween.TransitionType.FADE_IN, gui.getComponent(SpriteRenderer.class));
            States.GAME.pause();
        }

        @Override
        public void onPop()
        {
            scene.clearEntities();
            Timer.schedule(new Timer.Task()
            {
                @Override
                public void run()
                {
                    States.GAME.unpause();
                }
            }, 0.05f);
        }

        @Override
        public void update(float deltaTime)
        {
            scene.update(deltaTime);

            if (fadeIn.doTransition(0.05f))
            {
                if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
                    LPReboot.getInstance().getStateEngine().popState();

                if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
                {
                    LPReboot.getInstance().getStateEngine().popState();
                    LPReboot.getInstance().getStateEngine().popState();
                }
            }
        }
    };
}

package com.skettios.lpreboot.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.entity.Entity;
import com.skettios.lpreboot.entity.component.Script;
import com.skettios.lpreboot.entity.component.SpriteRenderer;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.gui.Gui;
import com.skettios.lpreboot.scene.Scene;
import com.skettios.lpreboot.scene.SceneGame;
import com.skettios.lpreboot.scene.SceneMainMenu;

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

                innerTable.add(new VisLabel("This is a test"));
                innerTable.align(Align.topLeft);
                innerTable.padLeft(10);
                innerTable.padTop(10);

                table.align(Align.bottom);
                table.add(innerTable);
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

        private Entity background = new Entity();

        @Override
        public void onPush()
        {
            background.addComponent(new SpriteRenderer(new Texture(Gdx.files.absolute("assets/textures/pause_background.png")), RenderEngine.RenderType.WINDOW_GUI).setAlpha(0.5f));

            scene.addEntity(background);

            States.GAME.pause();
        }

        @Override
        public void onPop()
        {
            States.GAME.unpause();
        }

        @Override
        public void update(float deltaTime)
        {
        }
    };
}

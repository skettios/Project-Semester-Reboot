package com.skettios.lpreboot.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.skettios.lpreboot.entity.Entity;
import com.skettios.lpreboot.entity.component.Script;
import com.skettios.lpreboot.entity.component.SpriteRenderer;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.gui.GuiGame;

public class SceneGame extends Scene
{
    private Entity player = new Entity();
    private Entity gameBG = new Entity();
    private Entity windowBG = new Entity();

    private GuiGame gui = new GuiGame();

    @Override
    public void initialize()
    {
        player.addComponent(new SpriteRenderer(new Texture(Gdx.files.absolute("assets/textures/player.png")), RenderEngine.RenderType.GAME_FG));
        player.addComponent(new Script(Gdx.files.absolute("assets/scripts/player_logic.lua")));
        windowBG.addComponent(new SpriteRenderer(new Texture(Gdx.files.absolute("assets/textures/background.png")), RenderEngine.RenderType.WINDOW_BG));
        gameBG.addComponent(new SpriteRenderer(new Texture(Gdx.files.absolute("assets/textures/game_background.png")), RenderEngine.RenderType.GAME_BG).setColor(Color.DARK_GRAY).setAlpha(0.75f));

//        addEntity(gui);
        addEntity(player.transform.setPosition(175, 75));
        addEntity(windowBG);
        addEntity(gameBG);
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        if (Gdx.input.isKeyJustPressed(Input.Keys.END))
            addEntity(gui);
    }
}

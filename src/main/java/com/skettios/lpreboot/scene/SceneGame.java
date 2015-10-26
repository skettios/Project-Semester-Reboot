package com.skettios.lpreboot.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.skettios.lpreboot.entity.Entity;
import com.skettios.lpreboot.entity.component.BehaviorScript;
import com.skettios.lpreboot.entity.component.SpriteRenderer;
import com.skettios.lpreboot.gfx.RenderEngine;
import com.skettios.lpreboot.gfx.TransitionTween;
import com.skettios.lpreboot.gfx.TransitionTween.TransitionType;
import com.skettios.lpreboot.gui.GuiGame;
import com.skettios.lpreboot.gui.GuiGameWindow;
import com.skettios.lpreboot.util.Assets;

public class SceneGame extends Scene
{
    private Entity player = new Entity();
    private Entity gameBG = new Entity();
    private Entity windowBG = new Entity();
    private Entity seanDeath = new Entity();

    private GuiGame gui;
    private GuiGameWindow guiWindow;

    private TransitionTween transition;
    
	@Override
	public void loadContent()
	{
		Assets.startLoading();
		Assets.loadTexture("background");
		Assets.loadTexture("player");
		Assets.loadTexture("game_background");
		Assets.loadTexture("textbar");
		Assets.loadTexture("sidebar");
		Assets.loadScript("player");
		Assets.loadTexture("menu_background");
		Assets.loadTexture("death");
		Assets.loadTexture("muertos");
		Assets.endLoading();
	}

	@Override
	public void unloadContent()
	{
		Assets.unloadTexture("background");
		Assets.unloadTexture("player");
		Assets.unloadTexture("game_background");
		Assets.unloadTexture("textbar");
		Assets.unloadTexture("sidebar");
		Assets.unloadScript("player");
		Assets.unloadTexture("menu_background");
		Assets.unloadTexture("death");
		Assets.unloadTexture("muertos");
	}
    
    @Override
    public void initialize()
    {	
    	gui = new GuiGame();
    	guiWindow = new GuiGameWindow();
    	
        player.addComponent(new SpriteRenderer(Assets.getTexture("player"), RenderEngine.RenderType.GAME_FG));
        player.addComponent(new BehaviorScript("player"));
        windowBG.addComponent(new SpriteRenderer(Assets.getTexture("background"), RenderEngine.RenderType.WINDOW_BG));
        gameBG.addComponent(new SpriteRenderer(Assets.getTexture("game_background"), RenderEngine.RenderType.GAME_BG).setColor(Color.DARK_GRAY).setAlpha(0.5f));
        seanDeath.addComponent(new SpriteRenderer(Assets.getTexture("death"), RenderEngine.RenderType.GAME_BG));
        seanDeath.transform.setPosition(100, 100);
        
        addEntity(player.transform.setPosition(170, 185));
        addEntity(windowBG);
        addEntity(gameBG);
        addEntity(guiWindow);
        addEntity(seanDeath);
        
        transition = new TransitionTween(TransitionType.FADE_OUT, seanDeath.getComponent(SpriteRenderer.class));
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        if (transition.doTransition(0.005f))
        {
    		windowBG.getComponent(SpriteRenderer.class).setTexture(Assets.getTexture("muertos"));
    		addEntity(gui);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.INSERT))
        	windowBG.getComponent(SpriteRenderer.class).setTexture(Assets.getTexture("menu_background"));
    }
}

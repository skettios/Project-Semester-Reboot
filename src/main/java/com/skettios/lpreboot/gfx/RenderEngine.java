package com.skettios.lpreboot.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.*;

import java.util.LinkedList;
import java.util.List;

public class RenderEngine
{
    public enum RenderType
    {
        WINDOW_BG,
        WINDOW_GUI,
        GAME_BG,
        GAME_FG,
        GAME_GUI
    }

    private List<RenderLayer> layers = new LinkedList<RenderLayer>();
    private SpriteBatch batch = new SpriteBatch();

    private Viewport windowView;
    private Viewport gameView;

    private RenderLayer windowBG;
    private RenderLayer windowGUI;
    private RenderLayer gameBG;
    private RenderLayer gameFG;
    private RenderLayer gameGUI;

    public RenderEngine()
    {
        windowView = new ScalingViewport(Scaling.none, 800, 600);
        gameView = new ScalingViewport(Scaling.none, 400, 570);

        layers.add(windowBG = new RenderLayer(RenderType.WINDOW_BG));
        layers.add(gameBG = new RenderLayer(RenderType.GAME_BG));
        layers.add(gameFG = new RenderLayer(RenderType.GAME_FG));
        layers.add(gameGUI = new RenderLayer(RenderType.GAME_GUI));
        layers.add(windowGUI = new RenderLayer(RenderType.WINDOW_GUI));
    }

    public void add(IRenderer renderer)
    {
        switch (renderer.getType())
        {
            case WINDOW_BG:
                windowBG.add(renderer);
                break;
            case WINDOW_GUI:
                windowGUI.add(renderer);
                break;
            case GAME_BG:
                gameBG.add(renderer);
                break;
            case GAME_FG:
                gameFG.add(renderer);
                break;
            case GAME_GUI:
                gameGUI.add(renderer);
                break;
            default:
                break;
        }
    }

    public void remove(IRenderer renderer)
    {
        switch (renderer.getType())
        {
            case WINDOW_BG:
                windowBG.remove(renderer);
                break;
            case WINDOW_GUI:
                windowGUI.remove(renderer);
                break;
            case GAME_BG:
                gameBG.remove(renderer);
                break;
            case GAME_FG:
                gameFG.remove(renderer);
                break;
            case GAME_GUI:
                gameGUI.remove(renderer);
                break;
            default:
                break;
        }
    }

    public void render(float deltaTime)
    {
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT | Gdx.gl20.GL_DEPTH_BUFFER_BIT);

        windowView.setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        windowView.apply();

        batch.begin();
        windowBG.render(batch, deltaTime);
        batch.end();

        gameView.setScreenPosition(15, 15);
        gameView.setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameView.apply();

        batch.begin();
        gameBG.render(batch, deltaTime);
        batch.end();

        batch.begin();
        gameFG.render(batch, deltaTime);
        batch.end();

        batch.begin();
        gameGUI.render(batch, deltaTime);
        batch.end();

        windowView.setScreenSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        windowView.apply();

        batch.begin();
        windowGUI.render(batch, deltaTime);
        batch.end();

        System.out.println(batch.totalRenderCalls);
    }
}

package com.skettios.lpreboot.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.entity.Entity;
import com.skettios.lpreboot.gfx.RenderEngine;

public abstract class Gui extends Entity
{
    public GuiRenderer renderer;
    public Stage stage;
    public Table table;

    public Gui(RenderEngine.RenderType type)
    {
        super();

        stage = new Stage();
        table = new Table(VisUI.getSkin());
        renderer = addComponent(new GuiRenderer(this, type));

        stage.setViewport(LPReboot.getInstance().getRenderEngine().getViewForType(type));

        initialize();

        stage.setDebugAll(true);
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        stage.act(deltaTime);
    }

    public abstract void initialize();
}

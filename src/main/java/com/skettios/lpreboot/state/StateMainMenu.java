package com.skettios.lpreboot.state;

import com.skettios.lpreboot.LPReboot;
import com.skettios.lpreboot.scene.SceneMainMenu;

public class StateMainMenu extends State
{
    public StateMainMenu()
    {
        super(new SceneMainMenu());
    }

    @Override
    public void onPush()
    {
    }

    @Override
    public void onPop()
    {
        ((SceneMainMenu) scene).gui.setInputFocus(null);
        LPReboot.getInstance().getStateEngine().pushState(States.game);
    }
}

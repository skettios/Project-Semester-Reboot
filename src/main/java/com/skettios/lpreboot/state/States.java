package com.skettios.lpreboot.state;

public class States
{
    public static State mainMenu;
    public static State game;
    public static State paused;

    public static void initialize()
    {
        mainMenu = new StateMainMenu();
        game = new StateGame();
        paused = new StatePaused();
    }
}

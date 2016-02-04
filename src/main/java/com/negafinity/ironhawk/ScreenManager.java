package com.negafinity.ironhawk;

import com.negafinity.ironhawk.screens.*;

import java.util.HashMap;

public class ScreenManager
{
	public HashMap<STATE, Screen> screens = new HashMap<>();
	public STATE currentScreen;

	public enum STATE
	{
		MENU, GAME, HELP, GAMEOVER, START, IRONHAWK, CHOICEMENU, CONTROLS, LOGIN
	}

	public ScreenManager()
	{
		this.initializeScreens();
		this.currentScreen = STATE.MENU;
	}

	public void initializeScreens()
	{
		screens.put(STATE.MENU, new ScreenMenu());
		screens.put(STATE.START, new ScreenStart());
		screens.put(STATE.IRONHAWK, new ScreenIronHawk());
		screens.put(STATE.CONTROLS, new ScreenControls());
		screens.put(STATE.GAMEOVER, new ScreenGameOver());
		screens.put(STATE.LOGIN, new ScreenLogin());
		screens.put(STATE.HELP, new ScreenHelp());
		screens.put(STATE.CHOICEMENU, new ScreenChoiceMenu());
	}
}

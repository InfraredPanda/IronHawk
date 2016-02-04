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
		screens.put(STATE.MENU, new Menu());
		screens.put(STATE.START, new Start());
		screens.put(STATE.IRONHAWK, new IronHawk());
		screens.put(STATE.CONTROLS, new Controls());
		screens.put(STATE.GAMEOVER, new GameOver());
		screens.put(STATE.LOGIN, new Login());
		screens.put(STATE.HELP, new Help());
		screens.put(STATE.CHOICEMENU, new ChoiceMenu());
	}
}

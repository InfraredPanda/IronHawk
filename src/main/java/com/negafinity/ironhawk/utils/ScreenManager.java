package com.negafinity.ironhawk.utils;

import com.negafinity.ironhawk.screens.Screen;
import com.negafinity.ironhawk.screens.ScreenChoiceMenu;
import com.negafinity.ironhawk.screens.ScreenControls;
import com.negafinity.ironhawk.screens.ScreenGameOver;
import com.negafinity.ironhawk.screens.ScreenHelp;
import com.negafinity.ironhawk.screens.ScreenIronHawk;
import com.negafinity.ironhawk.screens.ScreenLogin;
import com.negafinity.ironhawk.screens.ScreenMenu;
import com.negafinity.ironhawk.screens.ScreenStart;

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
		this.currentScreen = STATE.START;
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

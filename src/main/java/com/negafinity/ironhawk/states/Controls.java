package com.negafinity.ironhawk.states;

import java.awt.Graphics;

import com.negafinity.ironhawk.Game;

public class Controls
{
	public void render(Graphics g, Game game)
	{
		g.drawImage(game.controlscreen, 0, 0, game);
	}

}

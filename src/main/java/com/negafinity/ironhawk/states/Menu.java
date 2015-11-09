package com.negafinity.ironhawk.states;

import com.negafinity.ironhawk.Game;

import java.awt.Font;
import java.awt.Graphics;

public class Menu
{
	public void render(Graphics g, Game game)
	{
		g.drawImage(game.ironhawklogo, 0, 0, game);

		Font fnt0 = new Font("arial", Font.BOLD, 12);
		g.setFont(fnt0);
		g.drawString("Version " + Game.VERSION, 550, 460);
	}

}

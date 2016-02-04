package com.negafinity.ironhawk.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.negafinity.ironhawk.Game;

public class GameOver extends Screen
{
	public Rectangle playAgain = new Rectangle(Game.WIDTH / 2 + 120, 250, 130, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);

	@Override
	public void render(Graphics g, Game game)
	{
		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font("arial", Font.BOLD, 75);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("GAME OVER!", 100, 100);

		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Again!", playAgain.x + 19, playAgain.y + 30);
		g2d.draw(playAgain);

		if (Game.players.size() == 1)
		{
			g.drawString("Your score was " + Game.getRoundNumber() + "! Your highscore is " + Game.players.get(0).getUser().getHighscore(), 5, 150);
		}
		else
		{
			;
		}

		g.drawString("Quit", quitButton.x + 19, quitButton.y + 30);
		g2d.draw(quitButton);
	}
}

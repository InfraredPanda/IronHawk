package com.negafinity.ironhawk.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.negafinity.ironhawk.IronHawk;

public class ScreenGameOver extends Screen
{
	public Rectangle playAgain = new Rectangle(IronHawk.WIDTH / 2 + 120, 250, 130, 50);
	public Rectangle quitButton = new Rectangle(IronHawk.WIDTH / 2 + 120, 350, 100, 50);

	@Override
	public void render(Graphics g, IronHawk game)
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

		if (IronHawk.players.size() == 1)
		{
			g.drawString("Your score was " + IronHawk.getRoundNumber() + "! Your highscore is " + IronHawk.players.get(0).getUser().getHighscore(), 5, 150);
		}
		else
		{
			;
		}

		g.drawString("Quit", quitButton.x + 19, quitButton.y + 30);
		g2d.draw(quitButton);
	}
}

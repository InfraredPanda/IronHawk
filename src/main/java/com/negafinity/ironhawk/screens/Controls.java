package com.negafinity.ironhawk.screens;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.negafinity.ironhawk.Game;

public class Controls extends Screen
{
	public Rectangle backButton = new Rectangle(5, 5, 100, 50);

	@Override
	public void render(Graphics g, Game game)
	{
		g.drawImage(game.controlscreen, 0, 0, game);
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.drawString("Back", backButton.x + 19, backButton.y + 30);
		g2d.draw(backButton);
	}

}

package com.negafinity.ironhawk.screens;

import com.negafinity.ironhawk.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ScreenHelp extends Screen
{
	public Rectangle backButton = new Rectangle(5, 5, 100, 50);
	public Rectangle controlsButton = new Rectangle(450, 5, 150, 50);

	@Override
	public void render(Graphics g, Game game)
	{
		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Help Menu", Game.WIDTH / 2 + 100, 50);

		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Back", backButton.x + 19, backButton.y + 30);
		g2d.draw(backButton);
		g.drawString("Controls", controlsButton.x + 13, controlsButton.y + 30);
		g2d.draw(controlsButton);

		Font fnt2 = new Font("arial", Font.PLAIN, 20);
		g.setFont(fnt2);
		g.setColor(Color.WHITE);

		// g.drawString("The controls for this game are very simple, Player 1 will always move", 30, 85);
		// g.drawString("with WASD. If you have two players, P2 will use the arrow keys.", 30, 105);
		g.drawString("Enemies will spawn at the beginning of every round, so watch out!", 30, 135);
		g.drawString("To defend from the enemies, dodge or hit 'Space' to fire rounds!", 30, 155);
		g.drawString("but don't let the enemies hit you, or you will lose health!", 30, 175);
		g.drawString("Every game you start with 100 health, so don't let the enemies hit you!", 30, 205);
		g.drawString("The rounds will get progressively harder, so watch out!", 30, 225);

	}

}

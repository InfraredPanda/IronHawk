package com.negafinity.ironhawk.states;

import com.negafinity.ironhawk.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ChoiceMenu
{
	public Rectangle player1Button = new Rectangle(Game.WIDTH / 2 + 120, 150, 200, 100);
	public Rectangle player2Button = new Rectangle(Game.WIDTH / 2 + 320, 150, 200, 100);

	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("1 Player", player1Button.x + 19, player1Button.y + 30);
		g2d.draw(player1Button);
		
		g.setColor(Color.white);
		g.drawString("2 Players", player2Button.x + 19, player2Button.y + 30);
		g2d.draw(player2Button);
	}

}

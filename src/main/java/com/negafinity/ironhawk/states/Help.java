package com.negafinity.ironhawk.states;

import com.negafinity.ironhawk.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help
{
	public Rectangle backButton = new Rectangle(5, 5, 100, 50);

	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Help Menu", Game.WIDTH / 2, 50);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Back", backButton.x + 19, backButton.y + 30);
		g2d.draw(backButton);
		
		Font fnt2 = new Font("arial", Font.PLAIN, 20);
		g.setFont(fnt2);
		g.setColor(Color.WHITE);
		g.drawString("In order to move in this game, you can either use W, A, S, D" , 30, 85);
		g.drawString("or, the arrow keys.", 30, 105);
		g.drawString("Enemies will spawn at the beginning of every round, so watch out!", 30, 135);
		g.drawString("To defend from the enemies, dodge or hit 'Space' to fire rounds!", 30, 155);
		g.drawString("but don't let the enemies hit you, or you will lose health!", 30, 175);
		g.drawString("Every game you start with 100 health, so don't let the enemies hit you!", 30, 205);

	}

}

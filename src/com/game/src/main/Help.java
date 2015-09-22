package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
	public Rectangle backButton = new Rectangle(5, 5, 100, 50);
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		
		Font fnt0 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Help Menu", Game.WIDTH/2, 50);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Back", backButton.x +19, backButton.y + 30);
		g2d.draw(backButton);
		
		
	}

}
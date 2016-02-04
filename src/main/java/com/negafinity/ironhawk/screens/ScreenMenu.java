package com.negafinity.ironhawk.screens;

import com.negafinity.ironhawk.IronHawk;

import java.awt.Font;
import java.awt.Graphics;

public class ScreenMenu extends Screen
{
	@Override
	public void render(Graphics g, IronHawk game)
	{
		g.drawImage(IronHawk.imageManager.ironhawklogo, 0, 0, game);

		Font fnt0 = new Font("arial", Font.BOLD, 12);
		g.setFont(fnt0);
		g.drawString("Version " + IronHawk.VERSION, 550, 460);
		if (ScreenLogin.user1LoggedIn)
		{
			g.drawString("Player 1 Logged In As : " + IronHawk.players.get(0).getUser().getUsername(), 25, 30);
		}
		else
		{
			g.drawString("No Player is Currently Logged In.", 450, 30);
		}
		if (ScreenLogin.user2LoggedIn)
		{
			g.drawString("Player 2 Logged In As : " + IronHawk.players.get(1).getUser().getUsername(), 350, 30);
		}
	}
}

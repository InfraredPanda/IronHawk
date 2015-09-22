package com.game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener
{

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		// TODO Auto-generated method stub
		// Play Button
		if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220)
		{
			if (my >= 150 && my <= 200)
			{
				// Pressed Play
				Game.State = Game.STATE.GAME;
			}
		}
		if (Game.State == Game.STATE.MENU)
		{
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220)
			{
				if (my >= 250 && my <= 300)
				{
					// Pressed Help
					Game.State = Game.STATE.HELP;
				}
			}
		}
		// Quit Button
		if (Game.State == Game.STATE.GAME || Game.State == Game.STATE.GAMEOVER)
		{
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220)
			{
				if (my >= 350 && my <= 400)
				{
					// Pressed Quit
					System.exit(1);
				}
				if (Game.State == Game.STATE.GAMEOVER)
				{
					if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 250)
					{
						if (my >= 250 && my <= 300)
						{
							// Pressed Play Again
							Game.HEALTH = 200;
							Game.State = Game.STATE.GAME;
						}
					}
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
